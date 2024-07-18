package az.company.cardproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CardProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardProjectApplication.class, args);

    }

}
