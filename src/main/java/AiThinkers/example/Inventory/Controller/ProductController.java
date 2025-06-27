package AiThinkers.example.Inventory.Controller;




import AiThinkers.example.Inventory.Entity.Product;
import AiThinkers.example.Inventory.Entity.User;
import AiThinkers.example.Inventory.Model.BuyProductRequest;
import AiThinkers.example.Inventory.Model.ProductPatchRequest;
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

    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody BuyProductRequest request){
        return productService.buyProduct(request);
    }

    @PatchMapping("/Update/{id}")
    public ResponseEntity<?> patchUpdateByProductId(@PathVariable Integer id,
    @RequestBody ProductPatchRequest patchRequest, Authentication authentication){

        User user = (User) authentication.getPrincipal();
        return productService.patchUpdateByProductId(id,patchRequest,user);
    }

    @PatchMapping("/Update-by-name/{name}")
    public ResponseEntity<?> patchUpdateByProductName(@PathVariable String name,
                                                      @RequestBody ProductPatchRequest patchRequest,
                                                      Authentication authentication){
        User user =(User) authentication.getPrincipal();
        return productService.patchUpdateByProductName(name, patchRequest,user);

    }

}