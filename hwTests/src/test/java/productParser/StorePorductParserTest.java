package productParser;

import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;
import product.Product;
import product.StoreProduct;
import productPriceCounter.ProductPriceCounter;
import productPriceCounter.StoreProductPriceCounter;
import productSerializer.ProductSerializer;
import productSerializer.StoreProductSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class StorePorductParserTest {
    private static Product product1 = new StoreProduct("D", 2.25);
    private static Product product2 = new StoreProduct("Z", 2.3, 3, 5.5);
    private static List<Product> productsList = new ArrayList<>();
    private ProductPriceCounter counter = new StoreProductPriceCounter();
    private ProductSerializer serializer = new StoreProductSerializer("products.json");
    private static Map<String, Integer> map = new HashMap<>();
    private ProductParser parser = new StoreProductParser(counter, serializer);

    @BeforeClass
    public static void fillList() {
        productsList.clear();
        map.clear();
        productsList.add(product1);
        productsList.add(product2);
    }

    @Test
    public void properStringTest() {
        assertTrue(parser.calculateTotalCost("ABCDABACCCCC") == 17.25);
    }

    @Test
    public void emptyStringTest() {
        assertTrue(parser.calculateTotalCost("") == null);
    }

    @Test
    public void lowercaseStringTest() {
        assertTrue(parser.calculateTotalCost("abcdABA") == null);
    }

    @Test
    public void extraSpacesStringTest() {
        assertTrue(parser.calculateTotalCost("ABCD  ABACCCCC") == null);
    }

    @Test
    public void otherSymbolsStringTest() {
        assertTrue(parser.calculateTotalCost("ABCD/ABACCCCC") == null);
    }

}
