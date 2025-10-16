package org.example.customerservice;

import org.example.customerservice.entity.Customer;
import org.example.customerservice.repository.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CustomerRepo customerRepo) {
        return args -> {
            customerRepo.save(Customer.builder().name("John Doe").email("John@gmail.com").build());
            customerRepo.save(Customer.builder().name("Jane Smith").email("Jane@gmail.com").build());
            customerRepo.save(Customer.builder().name("Alice Johnson").email("Alice@gmail.com").build());
        };
    }

}
