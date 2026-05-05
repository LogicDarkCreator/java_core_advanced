package com.customer.validator;

import com.customer.exception.InvalidPhoneException;
import java.util.regex.Pattern;

public class PhoneValidator {
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+?\\d{10,15}$");
    private static final String PHONE_EXAMPLE = "+71234567890 или 81234567890";

    public void validate(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new InvalidPhoneException(
                    "Телефон не может быть пустым",
                    "PhoneValidator: получен пустой телефон"
            );
        }

        String cleanPhone = phone.replaceAll("[\\s\\-()]", "");

        if (!PHONE_PATTERN.matcher(cleanPhone).matches()) {
            throw new InvalidPhoneException(
                    String.format("Неверный формат телефона: '%s'. Ожидается: %s (10-15 цифр)", phone, PHONE_EXAMPLE),
                    String.format("PhoneValidator: некорректный формат телефона: '%s' (очищенный: '%s')", phone, cleanPhone)
            );
        }
    }
}