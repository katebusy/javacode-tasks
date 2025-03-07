package org.example.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(schema = "shop", name = "orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Order {
    @Id
    private UUID id;
    @Column(name = "products")
    @JsonView(User.Views.UserDetails.class)
    private String products;
    @Column(name = "status")
    @JsonView(User.Views.UserDetails.class)
    private String status;
    @Column(name = "order_sum")
    @JsonView(User.Views.UserDetails.class)
    private BigDecimal sum;
//    @ManyToOne()
//    @JoinColumn(name = "user_id")
    @Column(name = "user_id")
    private UUID userId;
}
