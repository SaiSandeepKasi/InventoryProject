package AiThinkers.example.Inventory.Repo;

import AiThinkers.example.Inventory.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
