package com.customer.validator;

import com.customer.exception.InvalidPhoneException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {
    private PhoneValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PhoneValidator();
    }

    @Test
    void shouldValidateCorrectPhone() {
        assertDoesNotThrow(() -> validator.validate("+71234567890"));
        assertDoesNotThrow(() -> validator.validate("81234567890"));
        assertDoesNotThrow(() -> validator.validate("+123456789012345"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "   ",
            "abc123",
            "+12",
            "1234567890123456",
            "+7(123)456-78-90",
            "phone-number"
    })
    void shouldThrowExceptionForInvalidPhone(String invalidPhone) {
        assertThrows(InvalidPhoneException.class, () -> validator.validate(invalidPhone));
    }

    @Test
    void shouldThrowExceptionForNullPhone() {
        assertThrows(InvalidPhoneException.class, () -> validator.validate(null));
    }
}