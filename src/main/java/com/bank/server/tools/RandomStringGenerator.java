package com.bank.server.tools;

import java.util.Random;

public class RandomStringGenerator {

    public static String generateRandomCode(int length) {

        // Create a StringBuilder to build the random string
        StringBuilder randomString = new StringBuilder();

        // Create an instance of the Random class
        Random random = new Random();

        // Generate random digits and append them to the StringBuilder
        for (int i = 0; i < length; i++) {
            // Generate a random digit (0-9) and convert it to a character
            char randomDigit = (char) (random.nextInt(10) + '0');
            // Append the character to the StringBuilder
            randomString.append(randomDigit);
        }

        // Convert the StringBuilder to a String and return it
        return randomString.toString();
    }


}
