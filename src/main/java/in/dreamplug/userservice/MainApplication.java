package in.dreamplug.userservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;

@SpringBootApplication(scanBasePackages = {"in.dreamplug.userservice"})
@EnableJpaRepositories(basePackages = "in.dreamplug.userservice.repository")
@Slf4j
public class MainApplication {

    public static void main(String[] args) {
        log.info("Application starting :::" + LocalDateTime.now());
        SpringApplication.run(MainApplication.class, args);
        log.info("Application started :::" + LocalDateTime.now());
    }

}
