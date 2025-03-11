package org.example.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private UUID id;
    private LocalDateTime orderDate;
    @NotEmpty(message = "Address is mandatory")
    private String address;
    @NotEmpty(message = "Total price is mandatory")
    private BigDecimal total;
    @Pattern(regexp = "^(In Progress| Delivered)$", message = "Status must be either 'In Progress' or 'Delivered'")
    private String status;
    @NotEmpty(message = "Customer id is mandatory")
    private CustomerDTO customer;
    private List<ProductDTO> products;
}
