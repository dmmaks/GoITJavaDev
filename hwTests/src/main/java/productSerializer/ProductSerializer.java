package productSerializer;

import product.Product;

import java.util.List;

public interface ProductSerializer {
    public void serialize (List<Product> products);

    public List<Product> deserialize ();
}
