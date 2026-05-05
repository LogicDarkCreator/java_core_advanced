package com.customer.service;

import com.customer.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void addCustomer(String data);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomer(String name);
    boolean removeCustomer(String name);
    int getCustomCount();
}
