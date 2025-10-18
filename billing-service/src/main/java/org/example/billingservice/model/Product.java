package org.example.billingservice.model;

import lombok.Data;

@Data
public class Product {
    private String id;
    private String name;
    private int quantity;
    private double price;
}
