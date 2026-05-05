package com.customer.validator;

import com.customer.exception.InvalidEmailException;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final String EMAIL_EXAMPLE = "username@domain.zone";

    public void validate(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidEmailException(
                    "Email не может быть пустым",
                    "EmailValidator: получен пустой email"
            );
        }

        String trimmedEmail = email.trim();

        if (!EMAIL_PATTERN.matcher(trimmedEmail).matches()) {
            throw new InvalidEmailException(
                    String.format("Неверный формат email: '%s'. Ожидается формат: %s", email, EMAIL_EXAMPLE),
                    String.format("EmailValidator: некорректный формат email: '%s'", email)
            );
        }
    }
}