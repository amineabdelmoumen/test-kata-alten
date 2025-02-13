package ma.alten.testAlten.security.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor // Keep this if needed for JPA
@Table(name = "AUTHORITIES") // Changed table name to uppercase for consistency
public class Authorities implements Serializable, GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "authority")
    private String authority;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    @JsonIgnore
    private User user;

    @Override
    public String getAuthority() {
        return authority;
    }
    @Override
    public int hashCode() {
        return Objects.hash(authority);  // Example, use relevant fields
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authorities that = (Authorities) o;
        return Objects.equals(authority, that.authority);  // Example, use relevant fields
    }
}