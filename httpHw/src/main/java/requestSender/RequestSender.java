package requestSender;

import com.google.gson.Gson;
import models.enums.Method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class RequestSender {
    private final String baseUrl;
    private Gson gson = new Gson();

    public RequestSender(String basePath) {
        String baseUrl = new String("https://petstore.swagger.io/v2");
        if (!basePath.startsWith("/")) {
            baseUrl += "/";
        }
        baseUrl += basePath;
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        this.baseUrl = baseUrl;
    }

    protected String getBaseUrl() {
        return baseUrl;
    }

    private String toJson(Object obj) {
        return gson.toJson(obj);
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    protected String sendBasicRequest(String urlStr, Method method) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.toString());
            return outputResponse(connection);
        } catch (IOException e) {
            System.out.println("I/O Exception occurred.");
        }
        return null;
    }

    protected void sendUpdatingRequest(String urlStr, Method method, Object obj) {
        String json = toJson(obj);
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.toString());
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            OutputStream os = connection.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();
            outputResponse(connection);
        } catch (IOException e) {
            System.out.println("I/O Exception occurred.");
            return;
        }
    }

    protected String outputResponse(HttpURLConnection connection) throws IOException {
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
        return null;
    }
}
