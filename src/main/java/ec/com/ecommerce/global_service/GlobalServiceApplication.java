package ec.com.ecommerce.global_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "ec.com.ecommerce.global_service",
        "ec.com.ecommerce.pagination",
        "ec.com.ecommerce.rest",
        "ec.com.ecommerce.scanner"
})
@EntityScan(basePackages = {
        "ec.com.ecommerce.global_service",
        "ec.com.ecommerce.entities"
})
@OpenAPIDefinition(info = @Info(title = "Global services", version = "1.0", description = "Global services for E-commerce Platform"))
public class GlobalServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GlobalServiceApplication.class, args);
    }
}