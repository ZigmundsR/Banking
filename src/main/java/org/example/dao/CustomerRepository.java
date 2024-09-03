package org.example.dao;

import org.example.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByName(String name);
}
