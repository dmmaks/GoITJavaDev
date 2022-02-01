package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Order {
    private long id;
    private long petId;
    private int quantity;
    private Date date;
    private OrderStatus status;
    private boolean complete;
}
