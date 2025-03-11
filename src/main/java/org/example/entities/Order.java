package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "shop", name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(mappedBy = "orders")
    private List<Product> products = new ArrayList<>();

    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "address")
    private String shippingAddress;
    @Column(name = "total")
    private BigDecimal totalPrice;
    @Column(name = "status")
    private String orderStatus;
}
