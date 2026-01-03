package org.example.billingservice.web;

import org.example.billingservice.entity.Bill;
import org.example.billingservice.feign.CustomerClientRest;
import org.example.billingservice.feign.ProductClientRest;
import org.example.billingservice.repository.BillRepo;
import org.example.billingservice.repository.ProductItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class BillingRestController {
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private ProductItemRepo productItemRepo;
    @Autowired
    private CustomerClientRest customerClientRest;
    @Autowired
    private ProductClientRest productClientRest;

    @GetMapping("bills/{id}")
    public Bill getBillById(@PathVariable Long id){
        Bill bill = billRepo.findById(id).orElseThrow(()->new RuntimeException("Bill not found"));
        bill.setCustomer(customerClientRest.getCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productClientRest.getProductById(productItem.getProductId()));
        });
        return bill;
    }

    @GetMapping("bills")
    public List<Bill> getBills(){
        List<Bill> bills = billRepo.findAll();
        bills.forEach(bill -> {
            bill.setCustomer(customerClientRest.getCustomerById(bill.getCustomerId()));
            bill.getProductItems().forEach(productItem -> {
                productItem.setProduct(productClientRest.getProductById(productItem.getProductId()));
            });
        });
        return bills;
    }
}
