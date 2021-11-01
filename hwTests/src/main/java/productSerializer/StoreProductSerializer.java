package productSerializer;

import product.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import product.StoreProduct;

public class StoreProductSerializer implements ProductSerializer {
    private String filename;
    public StoreProductSerializer(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void serialize(List<Product> products) {
        try (FileWriter writer = new FileWriter(filename))
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(products);
            writer.write(json);
            writer.flush();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public List<Product> deserialize() {
        List<Product> products = new ArrayList<Product>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename));)
        {
            StringBuilder fileText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                fileText.append(line);
            }
            Gson gson = new Gson();
            Product[] productsArr = gson.fromJson(fileText.toString(), StoreProduct[].class);
            products = List.of(productsArr);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return products;
    }
}
