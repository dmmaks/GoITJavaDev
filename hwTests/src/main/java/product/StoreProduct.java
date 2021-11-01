package product;

public class StoreProduct implements Product {
    private String code;
    private double price;
    private Integer quantity;
    private Double discountPrice;

    public StoreProduct(String code, double price) {
        this.code = code;
        this.price = price;
    }

    public StoreProduct(String code, double price, Integer quantity, Double discountPrice) {
        this(code, price);
        this.quantity = quantity;
        this.discountPrice = discountPrice;
    }

    public String getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

    public Integer getDiscountQuantity() {
        return quantity;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
