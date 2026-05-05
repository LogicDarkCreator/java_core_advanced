package com.customer.command;

import com.customer.service.CustomerService;
import com.customer.service.CustomerStorage;

public class RemoveCommand implements Command {
    private final CustomerService customerService;

    public RemoveCommand() {
        this.customerService = new CustomerStorage();
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: Команда remove требует имя клиента");
            System.out.println("Пример: " + getExample());
            return;
        }

        boolean removed = customerService.removeCustomer(args[0]);
        if (removed) {
            System.out.println("Клиент \"" + args[0] + "\" успешно удален");
        } else {
            System.out.println("Клиент \"" + args[0] + "\" не найден");
        }
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Удалить клиента по имени";
    }

    @Override
    public String getExample() {
        return "remove Василий Петров";
    }
}