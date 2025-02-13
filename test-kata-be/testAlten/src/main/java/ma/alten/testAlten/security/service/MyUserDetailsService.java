package ma.alten.testAlten.security.service;

import ma.alten.testAlten.security.domain.Authorities;
import ma.alten.testAlten.security.domain.User;
import ma.alten.testAlten.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        Set<Authorities> authoritiesSet = user.getAuthorities();

        // Convert the user entity to a UserDetails object
        return UserDetailsImpl.builder()
                .password(user.getPassword())
                .username(user.getUsername())
                .email(user.getEmail())
                .authorities(authoritiesSet)// Add roles/authorities if needed
                .build();
    }

}