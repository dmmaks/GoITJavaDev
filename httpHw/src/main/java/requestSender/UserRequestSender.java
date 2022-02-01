package requestSender;

import models.User;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;

public class UserRequestSender extends RequestSender {
    public UserRequestSender() {
        super("/user/");
    }

    public void create (User user) {
        String json = toJson(user);
        int responseCode = 0;
        try {
            URL url = new URL(getBaseUrl());
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

    public String logout() {
        String urlStr = getBaseUrl() + "logout";
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

    public void login(String username, String password) {
        String urlStr = getBaseUrl() + "login?username=" + username + "&password=" + password;
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
            } else {
                System.out.println("Got error " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("I/O Exception occurred.");
        }
    }

    public void delete(String username) {
        String urlStr = getBaseUrl() + username;
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

    public String get(String username) {
        String urlStr = getBaseUrl() + username;
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

    public void update (String username, User user) {
        String json = toJson(user);
        int responseCode = 0;
        try {
            URL url = new URL(getBaseUrl() + user.getUsername());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
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

    public void createWithArray (Collection<User> users) {
        String json = toJson(users);
        int responseCode = 0;
        try {
            URL url = new URL(getBaseUrl() + "createWithArray");
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
}
