package productPriceCounter;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import product.Product;
import product.StoreProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class StoreProductPriceCounterTest {
    private static Product product1 = new StoreProduct("D", 2.25);
    private static Product product2 = new StoreProduct("Z", 2.3, 3, 5.5);
    private static Product product3 = new StoreProduct("", 2.3, 3, 5.5);
    private static Product product4 = new StoreProduct("Z", -2.3, 3, 5.5);
    private static Product product5 = new StoreProduct("Z", 2.3, -3, 5.5);
    private static Product product6 = new StoreProduct("Z", 2.3, 3, -5.5);
    private static List<Product> productsList = new ArrayList<>();
    private static List<Product> productsList1 = new ArrayList<>();
    private ProductPriceCounter counter = new StoreProductPriceCounter();
    private static Map<String, Integer> map = new HashMap<>();

    @Before
    public void fillList() {
        productsList.clear();
        productsList1.clear();
        map.clear();
        productsList.add(product1);
        productsList.add(product2);
        productsList1.add(product1);
        map.put("D", 2);
        map.put("Z", 4);
    }

    @Test
    public void properCountTest() {
        assertTrue(counter.count(productsList, map) == 12.3);
    }

    @Test
    public void emptyCodeTest() {
        productsList1.add(product3);
        assertTrue(counter.count(productsList1, map) == 0);
    }

    @Test
    public void negativePriceTest() {
        productsList1.add(product4);
        assertTrue(counter.count(productsList1, map) == 0);
    }

    @Test
    public void negativeQunatityTest() {
        productsList1.add(product5);
        assertTrue(counter.count(productsList1, map) == 0);
    }

    @Test
    public void negativeDiscountPriceTest() {
        productsList1.add(product6);
        assertTrue(counter.count(productsList1, map) == 0);
    }

    @Test
    public void noEntryInMapTest() {
        map.clear();
        assertTrue(counter.count(productsList, map) == 0);
    }

    @Test
    public void negativeMapValueTest() {
        map.put("D", -2);
        assertTrue(counter.count(productsList, map) == 0);
    }

    @Test
    public void noMapValueTest() {
        map.put("D", null);
        assertTrue(counter.count(productsList, map) == 0);
    }

}
