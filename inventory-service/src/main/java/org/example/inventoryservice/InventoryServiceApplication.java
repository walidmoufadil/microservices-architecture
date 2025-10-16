package org.example.inventoryservice;

import org.example.inventoryservice.entity.Product;
import org.example.inventoryservice.repository.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProductRepo productRepo) {
        return args -> {
            productRepo.save(Product.builder().id(UUID.randomUUID().toString()).name("Laptop").quantity(50).price(999.99).build());
            productRepo.save(Product.builder().id(UUID.randomUUID().toString()).name("Smartphone").quantity(200).price(499.99).build());
            productRepo.save(Product.builder().id(UUID.randomUUID().toString()).name("Tablet").quantity(150).price(299.99).build());
        };
    }

}
