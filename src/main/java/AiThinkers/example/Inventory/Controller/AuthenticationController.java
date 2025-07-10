package AiThinkers.example.Inventory.Controller;

import AiThinkers.example.Inventory.Model.LoginRequest;
import AiThinkers.example.Inventory.Model.SignupRequest;
import AiThinkers.example.Inventory.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest request) {
        return authService.registerUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        String token = authService.loginUser(request.getEmailOrMobile(), request.getPassword());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
