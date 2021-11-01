package productParser;

import productPriceCounter.ProductPriceCounter;
import productPriceCounter.StoreProductPriceCounter;
import productSerializer.*;

import java.util.HashMap;
import java.util.Map;

public class StoreProductParser  implements ProductParser{

    private ProductPriceCounter counter;
    private ProductSerializer serializer;
    public StoreProductParser(ProductPriceCounter counter, ProductSerializer serializer) {
        this.counter = counter;
        this.serializer = serializer;
    }
    public Double calculateTotalCost(String productString) {
        productString = productString.trim();
        if (!productString.matches("[A-Z]+")) {
            return null;
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        String[] codes = productString.split("(?!^)");
        for (String str : codes)
        {
            if(map.containsKey(str))
            {
                map.replace(str, map.get(str) + 1);
            }
            else
            {
                map.put(str, 1);
            }
        }
        return counter.count(serializer.deserialize(), map);
    }
}
