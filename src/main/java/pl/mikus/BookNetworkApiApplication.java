package pl.mikus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import pl.mikus.user.Role;
import pl.mikus.user.RoleRepository;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class BookNetworkApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookNetworkApiApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationStartedEvent> createUserRoleWhenApplicationStated(RoleRepository roleRepository) {
        return (event) -> {
            Role userRole = Role.builder().name("USER").build();
            roleRepository.findByName("USER").orElseGet(() -> roleRepository.save(userRole));
        };
    }
}
