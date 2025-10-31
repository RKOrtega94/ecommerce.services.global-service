package ec.com.ecommerce;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = { //
        "ec.com.ecommerce", //
        "ec.com.ecommerce.config", //
})
@OpenAPIDefinition(info = @Info(title = "Global services", //
        version = "1.0", //
        description = "Global services for E-commerce Platform" //
))
public class GlobalServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GlobalServiceApplication.class, args);
    }
}
