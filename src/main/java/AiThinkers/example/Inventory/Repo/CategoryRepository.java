package AiThinkers.example.Inventory.Repo;

import AiThinkers.example.Inventory.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// CategoryRepository.java
public interface CategoryRepository extends JpaRepository<Category, Integer> {
     Optional<Category> findByName(String name);
}
