package ma.alten.testAlten.security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.alten.testAlten.business.domain.ShoppingList;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "APP_USER") // Changed table name to avoid reserved keyword
@Builder
@AllArgsConstructor
@NoArgsConstructor // Keep this if needed for JPA
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)

    private Set<Authorities> authorities = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<ShoppingList> shoppingLists;

    // Helper method to manage bidirectional relationship
    public void addAuthority(Authorities authority) {
        authorities.add(authority);
        authority.setUser(this);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username);  // Exclude authorities here
    }
}