/*package AiThinkers.example.Inventory;

import AiThinkers.example.Inventory.Entity.*;
import AiThinkers.example.Inventory.Repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            RoleRepository roleRepo,
            CategoryRepository categoryRepo,
            UserRepository userRepo,
            BCryptPasswordEncoder encoder
    ) {
        return args -> {
            // Add roles
            if (roleRepo.findByName("ADMIN").isEmpty())
                roleRepo.save(new Role(1, "ADMIN"));
            if (roleRepo.findByName("SALESPERSON").isEmpty())
                roleRepo.save(new Role(2, "SALESPERSON"));
        };

            // Add categories
            String[] categories = { "Mobiles", "Audio", "Accessories", "Laptops", "Televisions" };
            for (String cat : categories) {
                if (categoryRepo.findByName(cat).isEmpty())
                    categoryRepo.save(new Category(cat));
            }

            // Add an admin user
            if (userRepo.findByEmail("admin@warehouse.com").isEmpty()) {
                Role adminRole = roleRepo.findByName("ADMIN").get();
                User admin = new User(
                        "Admin User",
                        "admin@warehouse.com",
                        "9999999999",
                        encoder.encode("admin123"), // password is 'admin123'
                        adminRole
                );
                userRepo.save(admin);
            }
        };
    }
*/