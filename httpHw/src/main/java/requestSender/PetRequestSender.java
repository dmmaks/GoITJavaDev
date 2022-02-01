package requestSender;

import models.Pet;
import models.enums.Method;
import models.enums.PetStatus;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.StringBody;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;

public class PetRequestSender extends RequestSender {

    public PetRequestSender() {
        super("/pet/");
    }

    public void update(Pet pet, Method method) {
        String json = toJson(pet);
        int responseCode = 0;
        try {
            URL url = new URL(getBaseUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (method == Method.POST) {
                connection.setRequestMethod("POST");
            } else {
                connection.setRequestMethod("PUT");
            }
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

    public void uploadImage(long petId) {
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG, PNG & GIF Images", "jpg", "gif", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal != JFileChooser.APPROVE_OPTION) {
                return;
            }
            URL url = new URL(getBaseUrl() + String.valueOf(petId) + "/uploadImage");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            File imageFile = new File(chooser.getSelectedFile().getAbsolutePath());
            String boundary_string = "some radom/arbitrary text";
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary_string);
            OutputStream conn_out = connection.getOutputStream();
            BufferedWriter conn_out_writer = new BufferedWriter(new OutputStreamWriter(conn_out));
            conn_out_writer.write("\r\n--" + boundary_string + "\r\n");
            conn_out_writer.write("Content-Disposition: form-data; " +
                    "name=\"image\"; " +
                    "filename=\"" + imageFile.getName() + "\"" +
                    "\r\n\r\n");
            conn_out_writer.flush();
            FileInputStream file_stream = new FileInputStream(imageFile);
            int read_bytes;
            byte[] buffer = new byte[1024];
            while ((read_bytes = file_stream.read(buffer)) != -1) {
                conn_out.write(buffer, 0, read_bytes);
            }
            conn_out.flush();
            conn_out_writer.write("\r\n--" + boundary_string + "--\r\n");
            conn_out_writer.flush();
            conn_out_writer.close();
            conn_out.close();
            file_stream.close();
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
            return;
        }

    }

    public String getByStatus(Collection<PetStatus> statuses) {
        String urlStr = getBaseUrl() + "findByStatus?status=";
        for (PetStatus status : statuses) {
            if (!urlStr.endsWith("=")) {
                urlStr += ",";
            }
            urlStr += status;
        }
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

    public String getByTags(Collection<String> tags) {
        String urlStr = getBaseUrl() + "findByTags?tags=";
        for (String tag : tags) {
            if (!urlStr.endsWith("=")) {
                urlStr += ",";
            }
            urlStr += tag;
        }
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

    public String getById(long id) {
        String urlStr = getBaseUrl() + id;
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

    public void updateWithFormData(long id, String name, PetStatus status) {
        int responseCode = 0;
        try {
            HttpEntity multipartEntity = org.apache.http.entity.mime.MultipartEntityBuilder.create()
                    .addPart("name", new StringBody(name, ContentType.TEXT_PLAIN))
                    .addPart("status", new StringBody(status.toString(), ContentType.TEXT_PLAIN))
                    .build();

            URL url = new URL(getBaseUrl() + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data");
            OutputStream os = connection.getOutputStream();
            multipartEntity.writeTo(os);
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

    public void delete(long id) {
        String urlStr = getBaseUrl() + id;
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
}
