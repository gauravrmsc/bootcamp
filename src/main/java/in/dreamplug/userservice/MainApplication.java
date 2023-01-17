package in.dreamplug.userservice;

import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        log.info("Application starting :::" + LocalDateTime.now());
        SpringApplication.run(MainApplication.class, args);
        log.info("Application started :::" + LocalDateTime.now());
    }
}



/*
 * Spring Actuator : https://docs.spring.io/spring-boot/docs/2.0.0.RELEASE/reference/htmlsingle/#production-ready-endpoints-enabling-endpoints
 * Important Actuator Endpoints
 * http://localhost:8082/user-service/actuator/health
 * http://localhost:8082/user-service/actuator/beans
 * http://localhost:8082/user-service/actuator/heapdump
 * */