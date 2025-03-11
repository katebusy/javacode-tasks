package org.example.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private UUID id; // или Long, в зависимости от используемого типа
    @NotEmpty(message = "Product name is mandatory")
    private String name;
    private String description;
    @NotEmpty(message = "Price is mandatory")
    private BigDecimal price;
    @NotEmpty(message = "Quantity is mandatory")
    private int quantity;
}
