package AiThinkers.example.Inventory;

import AiThinkers.example.Inventory.Entity.Role;
import AiThinkers.example.Inventory.Repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class RoleDataInitializer implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("SALESPERSON");
    }

    private void createRoleIfNotFound(String roleName) {
        if(!roleRepository.existsByName(roleName)){
            roleRepository.save(new Role(roleName));
        }
    }
}
