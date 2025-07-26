// filepath: /Users/ednax/coding/INF012-internet-banking-backend/internet-banking-main-api/src/main/java/edu/ifba/internet_banking_main_api/validators/CpfValidator.java
package edu.ifba.internet_banking_main_api.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<ValidCpf, String> {

    @Override
    public void initialize(ValidCpf constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }

        // Check if all digits are the same (invalid CPF)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calculate first verification digit
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit >= 10) firstDigit = 0;

        // Calculate second verification digit
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit >= 10) secondDigit = 0;

        // Verify both digits
        return Character.getNumericValue(cpf.charAt(9)) == firstDigit &&
               Character.getNumericValue(cpf.charAt(10)) == secondDigit;
    }
}