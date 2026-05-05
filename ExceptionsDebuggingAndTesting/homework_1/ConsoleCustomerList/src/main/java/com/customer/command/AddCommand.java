package com.customer.command;

import com.customer.service.CustomerService;
import com.customer.service.CustomerStorage;

public class AddCommand implements Command {
    private final CustomerService customerService;

    public AddCommand() {
        this.customerService = new CustomerStorage();
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: Команда add требует параметры");
            System.out.println("Пример: " + getExample());
            return;
        }
        customerService.addCustomer(args[0]);
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Добавить нового клиента";
    }

    @Override
    public String getExample() {
        return "add Василий Петров vasily.petrov@gmail.com +79215637722";
    }
}