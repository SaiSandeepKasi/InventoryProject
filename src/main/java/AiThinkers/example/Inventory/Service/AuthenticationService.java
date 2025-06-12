package AiThinkers.example.Inventory.Service;

import AiThinkers.example.Inventory.Entity.Role;
import AiThinkers.example.Inventory.Entity.User;
import AiThinkers.example.Inventory.Model.LoginRequest;
import AiThinkers.example.Inventory.Model.LoginResponse;
import AiThinkers.example.Inventory.Model.SignupRequest;
import AiThinkers.example.Inventory.Repo.RoleRepository;
import AiThinkers.example.Inventory.Repo.UserRepository;
import AiThinkers.example.Inventory.Security.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.http.ResponseEntity;
import java.util.Optional;

    @Service
    public class AuthenticationService {

        @Autowired
        private UserRepository userRepo;
        @Autowired private RoleRepository roleRepo;
        @Autowired private BCryptPasswordEncoder passwordEncoder;
        @Autowired private jwtUtil jwtUtil;

        public ResponseEntity<?> registerUser(SignupRequest request) {
            if (userRepo.findByEmail(request.getEmail()).isPresent() ||
                    userRepo.findByMobile(request.getMobile()).isPresent()) {
                return ResponseEntity.badRequest().body("User already exists");
            }
            Optional<Role> roleOpt = roleRepo.findByName(request.getRole());
            if (roleOpt.isEmpty())
                return ResponseEntity.badRequest().body("Invalid role");
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            User user = new User(request.getName(), request.getEmail(), request.getMobile(),
                    encodedPassword, roleOpt.get());
            userRepo.save(user);
            String token = jwtUtil.generateToken(user.getEmail());

            return ResponseEntity.ok(new LoginResponse(token));
        }

        public ResponseEntity<?> loginUser(LoginRequest request) {
            Optional<User> userOpt = userRepo.findByEmail(request.getEmailOrMobile());
            if (userOpt.isEmpty())
                userOpt = userRepo.findByMobile(request.getEmailOrMobile());
            if (userOpt.isEmpty())
                return ResponseEntity.badRequest().body("User not found");
            User user = userOpt.get();
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
                return ResponseEntity.badRequest().body("Invalid password");
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(new LoginResponse(token));
        }
    }

