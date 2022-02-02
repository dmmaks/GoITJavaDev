package requestSender;

import models.User;
import models.enums.Method;
import java.util.Collection;

public class UserRequestSender extends RequestSender {
    public UserRequestSender() {
        super("/user/");
    }

    public void create (User user) {
        sendUpdatingRequest(getBaseUrl(), Method.POST, user);
    }

    public String logout() {
        String urlStr = getBaseUrl() + "logout";
        return sendBasicRequest(urlStr, Method.GET);
    }

    public void login(String username, String password) {
        String urlStr = getBaseUrl() + "login?username=" + username + "&password=" + password;
        sendBasicRequest(urlStr, Method.GET);
    }

    public void delete(String username) {
        String urlStr = getBaseUrl() + username;
        sendBasicRequest(urlStr, Method.DELETE);
    }

    public String get(String username) {
        String urlStr = getBaseUrl() + username;
        return sendBasicRequest(urlStr, Method.GET);
    }

    public void update (String username, User user) {
        sendUpdatingRequest(getBaseUrl() + user.getUsername(), Method.PUT, user);
    }

    public void createWithArray (Collection<User> users) {
        sendUpdatingRequest(getBaseUrl() + "createWithArray", Method.POST, users);
    }
}
