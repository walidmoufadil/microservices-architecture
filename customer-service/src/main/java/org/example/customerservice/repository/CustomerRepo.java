package org.example.customerservice.repository;

import org.example.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
