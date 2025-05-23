package com.group2.InterviewManagement.utils;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?/{}~|";
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;
    private static final int PASSWORD_LENGTH = 12;
    private static final String PREFIX = "IMSD"; // Prefix requirement

    private static SecureRandom random = new SecureRandom();

    public static String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PREFIX); // Start with the prefix

        // Ensure the password has at least one uppercase letter, one lowercase letter, one digit, and one special character
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        // Fill the remaining characters randomly from ALL_CHARACTERS
        for (int i = PREFIX.length() + 4; i < PASSWORD_LENGTH; i++) {
            password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }

        // Shuffle the characters after the prefix to ensure randomness
        return PREFIX + shuffleString(password.substring(PREFIX.length()));
    }

    private static String shuffleString(String string) {
        char[] characters = string.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
}
