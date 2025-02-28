package me.verni;

import me.verni.user.UserRepository;
import me.verni.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "me.verni")
public class FinwiseApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinwiseApplication.class, args);

    }
}
