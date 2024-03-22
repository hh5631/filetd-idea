package demo.aa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class AaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AaApplication.class, args);
    }

}
