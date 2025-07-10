package AiThinkers.example.Inventory.Service;
import AiThinkers.example.Inventory.Repo.UserRepository;
//import com.example.warehouse.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepo.findByEmail(usernameOrEmail)
                .or(() -> userRepo.findByMobile(usernameOrEmail))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
