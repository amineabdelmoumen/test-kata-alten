package ma.alten.testAlten.business.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.alten.testAlten.security.domain.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="SHOPING_LIST")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
