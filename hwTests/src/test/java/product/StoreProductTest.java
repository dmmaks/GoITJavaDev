package product;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StoreProductTest {
    private Product product1 = new StoreProduct("D", 2.25);
    private Product product2 = new StoreProduct("Z", 2.3, 3, 5.5);

    @Test
    public void getProduct1PropertiesTest() {
        assertTrue(product1.getCode() == "D" && product1.getPrice() == 2.25);
        assertTrue(product1.getDiscountPrice() == null && product1.getDiscountQuantity() == null);
    }

    @Test
    public void getProduct2PropertiesTest() {
        assertTrue(product2.getCode() == "Z" && product2.getPrice() == 2.3);
        assertTrue(5.5 == product2.getDiscountPrice() && product2.getDiscountQuantity() == 3);
    }
}
