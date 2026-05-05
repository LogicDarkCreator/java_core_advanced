package com.customer.command;

import com.customer.model.Customer;
import com.customer.service.CustomerService;
import com.customer.service.CustomerStorage;
import java.util.List;

public class ListCommand implements Command {
    private final CustomerService customerService;

    public ListCommand() {
        this.customerService = new CustomerStorage();
    }

    @Override
    public void execute(String[] args) {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("Список клиентов пуст");
        } else {
            System.out.println("Список клиентов:");
            customers.forEach(System.out::println);
            System.out.println("Всего: " + customers.size());
        }
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Показать всех клиентов";
    }

    @Override
    public String getExample() {
        return "list";
    }
}