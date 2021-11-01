package productPriceCounter;

import product.Product;

import java.util.List;
import java.util.Map;

public class StoreProductPriceCounter implements ProductPriceCounter{
    public double count(List<Product> priceData, Map<String, Integer> quantityMap) {
        double totalPrice = 0;
        for (Product product : priceData) {
            double price = product.getPrice();
            if (product.getCode().length() == 0) {
                return 0;
            }
            Integer quantity = quantityMap.get(product.getCode());
            if (price <= 0 || quantity == null || quantity <= 0) {
                return 0;
            }
            if (quantity != null) {
                if (product.getDiscountQuantity() != null && product.getDiscountPrice() != null) {
                    if (product.getDiscountQuantity() <= 0 || product.getDiscountPrice() <= 0) {
                        return 0;
                    }
                    totalPrice += Math.floor(quantity/product.getDiscountQuantity()) * product.getDiscountPrice() + quantity % product.getDiscountQuantity() * price;
                }
                else {
                    totalPrice += quantity * price;
                }
            }
        }
        return totalPrice;
    }

}
