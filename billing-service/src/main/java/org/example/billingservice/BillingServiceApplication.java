package org.example.billingservice;

import org.example.billingservice.entity.Bill;
import org.example.billingservice.entity.ProductItem;
import org.example.billingservice.feign.CustomerClientRest;
import org.example.billingservice.feign.ProductClientRest;
import org.example.billingservice.model.Customer;
import org.example.billingservice.model.Product;
import org.example.billingservice.repository.BillRepo;
import org.example.billingservice.repository.ProductItemRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductItemRepo productItemRepo, BillRepo billRepo, CustomerClientRest customerClientRest, ProductClientRest productClientRest) {
        return args -> {
            Collection<Customer> customers = customerClientRest.getAllCustomers().getContent();
            Collection<Product> products = productClientRest.getAllProducts().getContent();

            customers.forEach(System.out::println);
            products.forEach(System.out::println);


            customers.forEach(customer -> {
                Bill bill = Bill.builder()
                        .billDate(new Date())
                        .customerId(customer.getId())
                        .build();
                billRepo.save(bill);
                products.forEach(product -> {
                    productItemRepo.save(
                            ProductItem.builder()
                                    .bill(bill)
                                    .productId(product.getId())
                                    .unitPrice(product.getPrice())
                                    .quantity(1 + (int)(Math.random() * 10))
                                    .build()
                    );
                });

            });
        };
    }

}
