package com.customer.command;

import com.customer.service.CustomerService;
import com.customer.service.CustomerStorage;

public class CountCommand implements Command {
    private final CustomerService customerService;

    public CountCommand() {
        this.customerService = new CustomerStorage();
    }

    @Override
    public void execute(String[] args) {
        int count = customerService.getCustomerCount();
        System.out.println("Всего клиентов: " + count);
    }

    @Override
    public String getName() {
        return "count";
    }

    @Override
    public String getDescription() {
        return "Показать количество клиентов";
    }

    @Override
    public String getExample() {
        return "count";
    }
}