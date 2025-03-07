package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.models.Order;
import org.example.models.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestUtils {
    public static User buildUser() {
        User user = User.builder()
                .id(UUID.randomUUID())
                .name("Example")
                .email("example@emal.com")
                .build();
        user.setOrders(createOrders(user.getId()));
        return user;
    }

    public static Order buildOrder(UUID userId) {
        return Order.builder()
                .id(UUID.randomUUID())
                .products("product1, product2, product3")
                .status("Delivered")
                .sum(BigDecimal.valueOf(Math.round(Math.random() * 10000)))
                .userId(userId)
                .build();
    }

    public static List<Order> createOrders(UUID userId) {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(buildOrder(userId));
        orders.add(buildOrder(userId));
        orders.add(buildOrder(userId));
        return orders;
    }

    @SneakyThrows
    public static String jsonStringify(Object obj) {
        return new ObjectMapper()
                .writeValueAsString(obj);
    }
}
