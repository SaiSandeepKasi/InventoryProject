package AiThinkers.example.Inventory.Service;



import AiThinkers.example.Inventory.Entity.*;
import AiThinkers.example.Inventory.Model.BuyProductRequest;
import AiThinkers.example.Inventory.Model.BuyProductRespone;
import AiThinkers.example.Inventory.Model.ProductPatchRequest;
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
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private CustomerRepository customerRepo;

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

    public ResponseEntity<?> getProductById(Integer id) {
        Optional<Product> productOpt = productRepo.findById(id);
        if(productOpt.isPresent()){
            return ResponseEntity.ok(productOpt.get());
        }else{
            return ResponseEntity.status(404).body("Product Not Found");
        }
    }

    public ResponseEntity<?> buyProduct(BuyProductRequest request) {
        //Finding the Product by name and category if provided
        Optional<Product> productOpt;
        if (request.getProductName() != null && !request.getCategoryName().isEmpty()) {

            //Finding by Category name in DB
            Optional<Category> categoryOpt = categoryRepo.findByName(request.getCategoryName());
            if (categoryOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Category Not Found");
            }
            //Finding by product & category or by product name id in DB
            productOpt = productRepo.findByNameAndCategory(request.getProductName(), categoryOpt.get());
        } else {
            productOpt = productRepo.findByName(request.getProductName());
        }
        if (productOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Prduct Not Found");
        }

        //Finding Customer id in DB
        Optional<Customer> customerOpt = customerRepo.findById(request.getCustomerId());
        if (customerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Customer Not Found");
        }

        Product product = productOpt.get();
        if (product.getStock() < request.getQuantity()) {
            return ResponseEntity.badRequest().body("Not enough stock available");
        }

        //This will set the product Quantity to current stock
        product.setStock(product.getStock() - request.getQuantity());
        productRepo.save(product);

        BuyProductRespone respone = new BuyProductRespone(
                product.getName(),
                product.getCategory().getName(),
                request.getQuantity(),
                product.getStock(),
                "Purchase was Successfully Done !!"
        );
        return ResponseEntity.ok(respone);
    }

    public ResponseEntity<?> patchUpdateByProductId(Integer id, ProductPatchRequest patchRequest, User user) {
        if(!user.getRole().getName().equals("ADMIN")){
            return ResponseEntity.status(400).body("Only Admin can Update the products");
        }

        Optional<Product> productOpt = productRepo.findById(id);
        if(productOpt.isEmpty()){
            return ResponseEntity.status(404).body("Product not Found By the Given Id");
        }

        Product product = productOpt.get();

        if(patchRequest.getName() != null)
            product.setName(patchRequest.getName());
        if(patchRequest.getPrice() != null)
            product.setPrice(patchRequest.getPrice());
        if(patchRequest.getStock() != null)
            product.setStock(patchRequest.getStock());
        if(patchRequest.getCategoryId() != null){
            Optional<Category> categoryOpt = categoryRepo.findById(patchRequest.getCategoryId());
            if(categoryOpt.isEmpty()){
                return ResponseEntity.badRequest().body("Category was found through the given Id ,Check the Id");
            }
            product.setCategory(categoryOpt.get());
        }
        productRepo.save(product);
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<?> patchUpdateByProductName(String name, ProductPatchRequest patchRequest, User user) {
        if(!user.getRole().getName().equals("ADMIN")){
            return ResponseEntity.status(400).body("Only Admin can update the products");
        }
        Optional<Product> productOpt = productRepo.findByName(name);
        if(productOpt.isEmpty()){
            return ResponseEntity.status(404).body("Product was not found with the given product name");
        }
        Product product = productOpt.get();

        if(patchRequest.getName() != null)
            product.setName(patchRequest.getName());
        if(patchRequest.getPrice() != null)
            product.setPrice(patchRequest.getPrice());
        if(patchRequest.getStock() != null)
            product.setStock(patchRequest.getStock());
        if(patchRequest.getCategoryId() != null){
            Optional<Category> categoryOpt = categoryRepo.findById(patchRequest.getCategoryId());
            if(categoryOpt.isEmpty()){
                return ResponseEntity.badRequest().body("Category was found through the given Id ,Check the Id");
            }
            product.setCategory(categoryOpt.get());
        }
        productRepo.save(product);
        return ResponseEntity.ok(product);
    }
}
