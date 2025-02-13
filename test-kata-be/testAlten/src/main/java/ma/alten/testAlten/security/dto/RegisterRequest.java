package ma.alten.testAlten.security.dto;

import lombok.Data;

/**
 * DTO for user registration requests.
 */
@Data
public class RegisterRequest {
    private String username;
    private String firstname;
    private String email;
    private String password;
}

