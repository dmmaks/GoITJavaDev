package requestSender;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

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
    protected String toJson(Object obj) {
        return gson.toJson(obj);
    }

    protected String getResponse(HttpURLConnection connection) throws IOException {
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
}
