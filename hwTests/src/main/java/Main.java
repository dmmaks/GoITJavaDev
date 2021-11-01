import productParser.StoreProductParser;
import productPriceCounter.StoreProductPriceCounter;
import productSerializer.*;
import product.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        ProductSerializer ser = new StoreProductSerilaizer();
//        Product p1 = new StoreProduct("A", 1.25, 3, 3.00);
//        Product p2 = new StoreProduct("B", 4.25);
//        Product p3 = new StoreProduct("C", 1.00, 6, 5.00);
//        Product p4 = new StoreProduct("D", 0.75);
//        List<Product> products = new ArrayList<>();
//        products.add(p1);
//        products.add(p2);
//        products.add(p3);
//        products.add(p4);
//        ser.serialize(products);
        System.out.println(new StoreProductParser(new StoreProductPriceCounter(), new StoreProductSerializer()).calculateTotalCost("ABCDABACCCCC"));
    }
}
