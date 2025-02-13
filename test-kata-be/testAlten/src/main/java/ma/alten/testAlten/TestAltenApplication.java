package ma.alten.testAlten;


import ma.alten.testAlten.business.domain.Product;
import ma.alten.testAlten.business.domain.enums.InventoryStatus;
import ma.alten.testAlten.business.repository.ProductRepository;
import ma.alten.testAlten.security.domain.Authorities;
import ma.alten.testAlten.security.domain.User;
import ma.alten.testAlten.security.repository.AuthoritiesRepository;
import ma.alten.testAlten.security.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TestAltenApplication {
	private final UserRepository userRepository;

    public TestAltenApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {

		SpringApplication.run(TestAltenApplication.class, args);
	}
	@Bean
	public List<User> setterUser(AuthoritiesRepository authoritiesRepository,  PasswordEncoder passwordEncoder,
								 ProductRepository productRepository) {
		String encodedPassword1 = passwordEncoder.encode("amine@2020");
		String encodedPassword2 = passwordEncoder.encode("amine11@2020");
		User user1=User.builder()
				.id(1L)
				.email("admin@admin.com")
				.password(encodedPassword1)
				.username("admin")
				.build();
		User user2=User.builder()
				.id(2L)
				.email("amine11@gmail.com")
				.password(encodedPassword2)
				.username("user")
				.build();

		userRepository.save(user1);
		userRepository.save(user2);
		 List<User> users = new ArrayList<>();
		users.add(user2);
		users.add(user1);
		Authorities authorities = new Authorities(1L, "ADMIN", "USER ADMIN",user1);
		Authorities authoritie2 = new Authorities(2L, "USER", "USER ADMIN",user2);
		authoritiesRepository.save(authorities);
		authoritiesRepository.save(authoritie2);
		Product product1=Product.builder()
				.id(1L).name("product1")
				.price(22.0).category("Clothes")
				.code("prd1")
				.inventoryStatus(InventoryStatus.INSTOCK).build();
		Product product2=Product.builder()
				.id(2L).name("product2")
				.price(23.0).category("Clothes")
				.code("prd2")
				.inventoryStatus(InventoryStatus.INSTOCK).build();
		productRepository.save(product1);
		productRepository.save(product2);
		return users;
	}

}
