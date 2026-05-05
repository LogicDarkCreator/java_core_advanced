package com.customer.service;

import com.customer.model.Customer;
import com.customer.validator.CustomerValidator;
import com.customer.exception.CustomerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerStorage implements CustomerService {
    private static final Logger queriesLogger = LogManager.getLogger("queries");
    private static final Logger errorsLogger = LogManager.getLogger("errors");

    private final Map<String, Customer> storage;
    private final CustomerValidator validator;

    public CustomerStorage() {
        this.storage = new ConcurrentHashMap<>();
        this.validator = new CustomerValidator();
    }

    @Override
    public void addCustomer(String data) {
        queriesLogger.info("Получен запрос на добавление клиента: {}", data);

        try {
            CustomerValidator.ValidationResult result = validator.validate(data);
            Customer customer = new Customer(result.getName(), result.getPhone(), result.getEmail());

            storage.put(result.getName(), customer);
            queriesLogger.info("Клиент успешно добавлен: {}", result.getName());
            System.out.println("Клиент успешно добавлен: " + result.getName());

        } catch (CustomerException e) {
            errorsLogger.error("Ошибка валидации: {}", e.getDetailedMessage());
            System.out.println("Ошибка: " + e.getUserMessage());
        } catch (Exception e) {
            errorsLogger.error("Непредвиденная ошибка при добавлении клиента", e);
            System.out.println("Произошла непредвиденная ошибка. Пожалуйста, попробуйте снова.");
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        queriesLogger.info("Запрошен список всех клиентов");
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Customer> getCustomer(String name) {
        queriesLogger.info("Запрос информации о клиенте: {}", name);
        return Optional.ofNullable(storage.get(name));
    }

    @Override
    public boolean removeCustomer(String name) {
        queriesLogger.info("Запрос на удаление клиента: {}", name);

        if (name == null || name.trim().isEmpty()) {
            errorsLogger.error("Попытка удаления с пустым именем");
            return false;
        }

        Customer removed = storage.remove(name.trim());
        if (removed != null) {
            queriesLogger.info("Клиент удален: {}", name);
            return true;
        } else {
            errorsLogger.error("Клиент не найден для удаления: {}", name);
            return false;
        }
    }

    @Override
    public int getCustomerCount() {
        queriesLogger.info("Запрос количества клиентов");
        return storage.size();
    }
}