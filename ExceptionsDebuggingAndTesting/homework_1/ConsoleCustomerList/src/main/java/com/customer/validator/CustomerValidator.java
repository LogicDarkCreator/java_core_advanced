package com.customer.validator;

import com.customer.exception.InvalidDataException;
import java.util.Arrays;

public class CustomerValidator {
    private static final int REQUIRED_COMPONENTS = 4;
    private static final int NAME_INDEX = 0;
    private static final int SURNAME_INDEX = 1;
    private static final int EMAIL_INDEX = 2;
    private static final int PHONE_INDEX = 3;

    private final EmailValidator emailValidator;
    private final PhoneValidator phoneValidator;

    public CustomerValidator() {
        this.emailValidator = new EmailValidator();
        this.phoneValidator = new PhoneValidator();
    }

    public ValidationResult validate(String inputData) {
        if (inputData == null || inputData.trim().isEmpty()) {
            throw new InvalidDataException(
                    "Введена пустая строка",
                    "CustomerValidator: получена пустая строка"
            );
        }

        String[] components = inputData.split("\\s+");

        if (components.length < REQUIRED_COMPONENTS) {
            throw new InvalidDataException(
                    String.format("Недостаточно данных. Требуется: Имя Фамилия Email Телефон. Получено: %d", components.length),
                    String.format("CustomerValidator: недостаточно компонентов. Ожидалось: %d, получено: %d",
                            REQUIRED_COMPONENTS, components.length)
            );
        }

        if (components.length > REQUIRED_COMPONENTS) {
            throw new InvalidDataException(
                    String.format("Слишком много данных. Требуется: Имя Фамилия Email Телефон. Получено: %d", components.length),
                    String.format("CustomerValidator: слишком много компонентов. Ожидалось: %d, получено: %d",
                            REQUIRED_COMPONENTS, components.length)
            );
        }

        String name = components[NAME_INDEX] + " " + components[SURNAME_INDEX];
        String email = components[EMAIL_INDEX];
        String phone = components[PHONE_INDEX];

        emailValidator.validate(email);
        phoneValidator.validate(phone);

        return new ValidationResult(name, email, phone);
    }

    public static class ValidationResult {
        private final String name;
        private final String email;
        private final String phone;

        public ValidationResult(String name, String email, String phone) {
            this.name = name;
            this.email = email;
            this.phone = phone;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
    }
}