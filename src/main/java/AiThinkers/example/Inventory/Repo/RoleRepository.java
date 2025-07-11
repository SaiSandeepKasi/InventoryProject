package AiThinkers.example.Inventory.Repo;


import AiThinkers.example.Inventory.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// RoleRepository.java
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

    boolean existsByName(String roleName);
}
