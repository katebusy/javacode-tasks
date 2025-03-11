package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "shop", name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "quantity")
    private int quantityInStock;

    @ManyToMany
    @JoinTable(
            name = "order_products", // Таблица связывания
            joinColumns = @JoinColumn(name = "product_id"), // Столбец для продуктов
            inverseJoinColumns = @JoinColumn(name = "order_id") // Столбец для заказов
    )
    private List<Order> orders = new ArrayList<>();;
}
