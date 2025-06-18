package AiThinkers.example.Inventory.Controller;




import AiThinkers.example.Inventory.Entity.Product;
import AiThinkers.example.Inventory.Entity.User;
import AiThinkers.example.Inventory.Service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Anyone logged in (admin or salesperson) can view products
    @GetMapping("/fetchAll")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<?>  getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }
    // Only admin can add a product
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return productService.addProduct(product, user);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return productService.deleteProduct(id, user);
    }

}
