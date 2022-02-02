package requestSender;

import models.Order;
import models.enums.Method;

public class StoreRequestSender extends RequestSender {
    public StoreRequestSender() {
        super("/store/");
    }

    public void create(Order order) {
        sendUpdatingRequest(getBaseUrl() + "order", Method.POST, order);
    }

    public String getById(long id) {
        String urlStr = getBaseUrl() + "order/" + id;
        return sendBasicRequest(urlStr, Method.GET);
    }

    public void delete(long id) {
        String urlStr = getBaseUrl() + "order/" + id;
        sendBasicRequest(urlStr, Method.DELETE);
    }

    public String getInventory() {
        String urlStr = getBaseUrl() + "inventory";
        return sendBasicRequest(urlStr, Method.GET);
    }
}
