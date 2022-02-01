package requestSender;

import models.Order;
import models.Pet;
import models.enums.Method;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class StoreRequestSender extends RequestSender {
    public StoreRequestSender() {
        super("/store/");
    }

    public void create(Order order) {
        String json = toJson(order);
        int responseCode = 0;
        try {
            URL url = new URL(getBaseUrl() + "order");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            OutputStream os = connection.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();

            responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Response:");
                String response = this.getResponse(connection);
                System.out.println(response);
            } else {
                System.out.println("Got error " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("I/O Exception occurred.");
            return;
        }
    }

    public String getById(long id) {
        String urlStr = getBaseUrl() + "order/" + id;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Response:");
                String response = this.getResponse(connection);
                System.out.println(response);
                return response;
            } else {
                System.out.println("Got error " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("I/O Exception occurred.");
        }
        return null;
    }

    public void delete(long id) {
        String urlStr = getBaseUrl() + "order/" + id;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Response:");
                String response = this.getResponse(connection);
                System.out.println(response);
            } else {
                System.out.println("Got error " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("I/O Exception occurred.");
        }
    }

    public String getInventory() {
        String urlStr = getBaseUrl() + "inventory";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Response:");
                String response = this.getResponse(connection);
                System.out.println(response);
                return response;
            } else {
                System.out.println("Got error " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("I/O Exception occurred.");
        }
        return null;
    }
}
