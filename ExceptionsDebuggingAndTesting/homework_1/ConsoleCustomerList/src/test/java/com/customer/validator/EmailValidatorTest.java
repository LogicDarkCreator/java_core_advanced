package com.customer.validator;

import com.customer.exception.InvalidEmailException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {
    private EmailValidator validator;

    @BeforeEach
    void setUp() {
        validator = new EmailValidator();
    }

    @Test
    void shouldValidateCorrectEmail() {
        assertDoesNotThrow(() -> validator.validate("user@example.com"));
        assertDoesNotThrow(() -> validator.validate("user.name@example.co.uk"));
        assertDoesNotThrow(() -> validator.validate("user+label@example.com"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "   ",
            "invalid-email",
            "user@",
            "@example.com",
            "user@.com",
            "user@example.",
            "user name@example.com"
    })
    void shouldThrowExceptionForInvalidEmail(String invalidEmail) {
        assertThrows(InvalidEmailException.class, () -> validator.validate(invalidEmail));
    }

    @Test
    void shouldThrowExceptionForNullEmail() {
        assertThrows(InvalidEmailException.class, () -> validator.validate(null));
    }
}