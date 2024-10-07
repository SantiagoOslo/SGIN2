package com.inventory.validation;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class InputValidator {

    
    public static boolean validateRequiredField(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Valida la longitud máxima de un campo de texto
    public static boolean validateMaxLength(String input, int maxLength) {
        if (input == null) return false;
        return input.length() <= maxLength;
    }

    // Valida que el texto no contenga caracteres no permitidos
    public static boolean validateSpecialCharacters(String input) {
        if (input == null) return false;
        // Permitir caracteres alfanuméricos y algunos caracteres especiales
        String allowedPattern = "^[a-zA-Z0-9 @#-]*$";
        return Pattern.matches(allowedPattern, input);
    }

    // Valida que un número no sea negativo
    public static boolean validateNonNegativeNumber(int number) {
        return number >= 0;
    }

    // Valida que un número esté en un rango permitido
    public static boolean validateNumberInRange(int number, int min, int max) {
        return number >= min && number <= max;
    }

    // Valida que el formato de la fecha sea válido (YYYY-MM-DD)
    public static boolean validateDateFormat(String date) {
        if (date == null) return false;
        String datePattern = "\\d{4}-\\d{2}-\\d{2}";
        return Pattern.matches(datePattern, date);
    }

    // Valida que la fecha no esté en el futuro
    public static boolean validateDateNotFuture(String date) {
        if (!validateDateFormat(date)) return false;
        // Convertir la fecha y compararla con la fecha actual
        LocalDate inputDate = LocalDate.parse(date);
        LocalDate currentDate = LocalDate.now();
        return !inputDate.isAfter(currentDate);
    }

    // Valida que el valor de precio no exceda un límite
    public static boolean validatePriceRange(double price, double maxPrice) {
        return price >= 0 && price <= maxPrice;
    }
}
