package org.example.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "shop", name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    public interface Views {
        interface UserSummary {
        }

        interface UserDetails extends UserSummary {
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name")
    @JsonView(Views.UserSummary.class)
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @Column(name = "email")
    @JsonView(Views.UserSummary.class)
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    @JsonView(Views.UserDetails.class)
    private List<Order> orders;

}
