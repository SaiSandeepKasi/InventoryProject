package AiThinkers.example.Inventory.Service;



import AiThinkers.example.Inventory.Entity.*;
import AiThinkers.example.Inventory.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;
    @Autowired private CategoryRepository categoryRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public ResponseEntity<?> addProduct(Product product, User user) {
        if (!user.getRole().getName().equals("ADMIN")) {
            return ResponseEntity.status(403).body("Only admin can add products");
        }
        Optional<Category> catOpt = categoryRepo.findById(product.getCategory().getId());
        if (catOpt.isEmpty())
            return ResponseEntity.badRequest().body("Category not found");
        product.setCategory(catOpt.get());
        productRepo.save(product);
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<?> deleteProduct(Integer id, User user) {
        if (!user.getRole().getName().equals("ADMIN")) {
            return ResponseEntity.status(403).body("Only admin can delete products");
        }
        Optional<Product> productOpt = productRepo.findById(id);
        if (productOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Product not found");
        }
        productRepo.delete(productOpt.get());
        return ResponseEntity.ok("Product deleted successfully");
    }

}
