package productPriceCounter;

import product.Product;

import java.util.List;
import java.util.Map;

public class StoreProductPriceCounter implements ProductPriceCounter{
    public double count(List<Product> priceData, Map<String, Integer> quantityMap) {
        double totalPrice = 0;
        for (Product product : priceData) {
            double price = product.getPrice();
            System.out.println("price " + price);
            Integer quantity = quantityMap.get(product.getCode());
            System.out.println("quantity " + quantity);
            if (quantity != null) {
                if (product.getDiscountQuantity() != null && product.getDiscountPrice() != null) {

                    System.out.println("test1 " + Math.floor(quantity/product.getDiscountQuantity()) );
                    totalPrice += Math.floor(quantity/product.getDiscountQuantity()) * product.getDiscountPrice() + quantity % product.getDiscountQuantity() * price;
                }
                else {
                    totalPrice += quantity * price;
                }
            }
            System.out.println("tot price " + totalPrice);
        }
        return totalPrice;
    }

}
