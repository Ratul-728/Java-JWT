package JWT.JWT;

import JWT.JWT.domain.Role;
import JWT.JWT.domain.User;
import JWT.JWT.service.RoleService;
import JWT.JWT.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService, RoleService roleService){
		return args -> {
			roleService.saveRole(new Role(1L, "ROLE_USER"));
			roleService.saveRole(new Role(2L, "ROLE_MANAGER"));
			roleService.saveRole(new Role(3L, "ROLE_ADMIN"));
			roleService.saveRole(new Role(4L, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(1L, "Ratul", "Ratul728", "123456", new ArrayList<>()));
			userService.saveUser(new User(2L, "Saif", "Saif728", "123", new ArrayList<>()));
			userService.saveUser(new User(3L, "Kashem", "Kashem728", "1234", new ArrayList<>()));

			userService.addRoleToUser("Ratul728", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("Ratul728", "ROLE_ADMIN");
			userService.addRoleToUser("Ratul728", "ROLE_MANAGER");

			userService.addRoleToUser("Saif728", "ROLE_MANAGER");

			userService.addRoleToUser("Kashem728", "ROLE_USER");
		};
	}

}
