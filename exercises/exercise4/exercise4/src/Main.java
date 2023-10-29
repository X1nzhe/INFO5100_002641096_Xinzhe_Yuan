/*
INFO5100 EXERCISE4
DATE: OCT/28/2023
STUDENT FULL NAME: Xinzhe Yuan
NUID: 002641096
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String[] patterns = {
                "\\b\\d{3}-\\d{3}-\\d{4}\\b",    // US Phone Number
                "[A-Z][a-z][a-z],\\d\\d*,\\d{4}", // Date
                "[A-Za-z]+",               // English words
                "\\b\\d{3}-\\d{2}-\\d{4}\\b", // US Social Security Number
                "\\d{5}",                  // US zipcode
        };

        String[] positiveTestCases = {
                "123-456-7890",
                "Oct,28,2023",
                "Information",
                "221-10-2315",
                "95110"
        };

        String[] negativeTestCases = {
                "1234-567-8901",
                "10/28/2023",
                "Info5100",
                "578456789",
                "12A34"
        };

        for (int i = 0; i < patterns.length; i++) {
            //Load Patterns
            Pattern pattern = Pattern.compile(patterns[i]);
            System.out.println("Pattern: " + patterns[i]);

            //Test Positive Cases
            System.out.println("Positive Test Cases:");
            Matcher matcherPositive = pattern.matcher(positiveTestCases[i]);
            System.out.println(positiveTestCases[i] + " : " + matcherPositive.matches());

            //Test Negative Cases
            System.out.println("Negative Test Cases:");
            Matcher matcherNegative = pattern.matcher(negativeTestCases[i]);
            System.out.println(negativeTestCases[i] + " : " + matcherNegative.matches());
            System.out.println();
        }
    }

    }
