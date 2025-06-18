package AiThinkers.example.Inventory.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;
/*@NotNull(message = "Price Should not be empty")
    @NotEmpty(Message="Price should not be Empty")
    we can also specify min and max price
    @Min(Value=10)
    @Max(Value=?)
    */
    private Double price;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id")   // Foreign key to Category table
    private Category category;

    // Constructors, getters, setters


    public Product(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product(int id, String name, Double price, Integer stock, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }
}
