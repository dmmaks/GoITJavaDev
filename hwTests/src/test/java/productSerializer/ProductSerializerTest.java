package productSerializer;

import org.junit.BeforeClass;
import org.junit.Test;
import product.Product;
import product.StoreProduct;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ProductSerializerTest {
    private static Product product1 = new StoreProduct("D", 2.25);
    private static Product product2 = new StoreProduct("Z", 2.3, 3, 5.5);
    private ProductSerializer serializer1 = new StoreProductSerializer("products.json");
    private ProductSerializer serializer2 = new StoreProductSerializer("something.json");
    private static List<Product> productsList = new ArrayList<>();

    @BeforeClass
    public static void fillList() {
        productsList.add(product1);
        productsList.add(product2);
    }

    @Test
    public void deserializationTest() {
        assertTrue(serializer1.deserialize().size() != 0);
        assertTrue(serializer2.deserialize().size() == 0);
        serializer2.serialize(productsList);
        assertTrue(serializer2.deserialize().size() != 0);
    }
}
