package AiThinkers.example.Inventory.Model;

public class BuyProductRespone {

    private String productName;
    private String categoryName;
    private int quantityBought;
    private int remainingStock;
    private String message;

    public BuyProductRespone() {
    }

    public BuyProductRespone(String productName, String categoryName, int quantityBought, int remainingStock, String message) {
        this.productName = productName;
        this.categoryName = categoryName;
        this.quantityBought = quantityBought;
        this.remainingStock = remainingStock;
        this.message = message;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQuantityBought() {
        return quantityBought;
    }

    public void setQuantityBought(int quantityBought) {
        this.quantityBought = quantityBought;
    }

    public int getRemainingStock() {
        return remainingStock;
    }

    public void setRemainingStock(int remainingStock) {
        this.remainingStock = remainingStock;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
