package org.example.service;

import org.example.Customer;
import org.example.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        Customer customerFromDB = customerRepository.findByName(customer.getName());
        if (customerFromDB != null) {
            customer = customerFromDB;
        } else {
            customerRepository.save(customer);
        }
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void updateCustomer(Customer customer){
        customerRepository.save(customer);
    }
}
