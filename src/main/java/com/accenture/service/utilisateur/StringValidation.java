package com.accenture.service.utilisateur;

import java.util.List;

public interface StringValidation {

    static boolean checkPassword(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }
        if (password.length() < 8 || password.length() > 16) {
            return false;
        }
        if (password.equals(password.toLowerCase())) {
            return false;
        }
        if (password.equals(password.toUpperCase())) {
            return false;
        }
        List<String> numbers = List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        boolean containNumber = false;
        for (String number : numbers) {
            if (password.contains(number)) {
                containNumber = true;
                break;
            }
        }
        List<String> specialCharacters = List.of("&", "#", "@", "-", "_", "§");
        boolean containSpecialCharacter = false;
        for (String specialCharacter : specialCharacters) {
            if (password.contains(specialCharacter)) {
                containSpecialCharacter = true;
                break;
            }
        }
        if (containNumber == false
                || containSpecialCharacter == false)
            return false;
        return true;
    }
}
