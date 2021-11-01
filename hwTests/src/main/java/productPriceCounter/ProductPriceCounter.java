package productPriceCounter;

import product.Product;

import java.util.List;
import java.util.Map;

public interface ProductPriceCounter {
    public double count(List<Product> priceData, Map<String, Integer> quantity);
}
