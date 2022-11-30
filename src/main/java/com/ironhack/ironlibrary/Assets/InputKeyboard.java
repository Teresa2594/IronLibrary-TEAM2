package com.ironhack.ironlibrary.Assets;

import java.util.Scanner;


public class InputKeyboard {

    final Scanner scanner = new Scanner(System.in);
    private static boolean exceptionIsActive = true;

    /**
     * @param name
     * @throws InputException
     * @Description this method verifies if the input is a string
     */
    static void isString(String name) throws InputException {
        if (name.matches("[+-]?\\d*(\\.\\d+)?") || name.length() == 0 || name.equals(" ")) {
            throw new InputException(Constants.ANSI_RED + "ERROR" + Constants.ANSI_RESET + " : Input not valid");
        }
    }

    /**
     * @param name
     * @return int
     * @throws InputException
     * @Description this method verifies if the input is an int
     */
    static int isInt(String name) throws InputException {
        try {
            return Integer.parseInt(name);
        } catch (NumberFormatException e) {
            throw new InputException(Constants.ANSI_RED + "ERROR" + Constants.ANSI_RESET + " : Input not valid");
        }
    }

    /**
     * @param question
     * @param test
     * @param scanner
     * @return String
     * @Description this method assures that the input is neither  null nor blanc,also that if there is an input
     * verifies if is a string
     */
    public static String checkString(String question, boolean test, Scanner scanner) {
        String name = "";
        while (exceptionIsActive) {
            System.out.println(Constants.ANSI_YELLOW + question + Constants.ANSI_RESET);
            try {
                name = test ? " " : scanner.nextLine();
                isString(name);
                exceptionIsActive = false;
            } catch (InputException e) {
                System.out.println(e.getMessage() + "\n");
                if (test) throw new InputException("");
            }
        }
        exceptionIsActive = true;
        return name;
    }

    /**
     * @param question
     * @param test
     * @param scanner
     * @return int
     * @Description this method assures that the input is neither  null nor blanc,also that if there is an input
     * verifies if is an int
     */
    public static int checkInt(String question, boolean test, Scanner scanner) {
        String input = "";
        int stringToInt = 0;
        while (exceptionIsActive) {
            System.out.println(Constants.ANSI_YELLOW + question + Constants.ANSI_RESET);
            try {
                input = test ? "" : scanner.nextLine();
                stringToInt = isInt(input);
                exceptionIsActive = false;
            } catch (InputException e) {
                System.out.println(e.getMessage());
                if (test) throw new InputException("");
            }

        }
        exceptionIsActive = true;
        return stringToInt;
    }

    /**
     * @param question
     * @param test
     * @param scanner
     * @return String
     * @Description this method assures that the input is a string of only numbers
     */
    public static String checkFakeInt(String question, boolean test, Scanner scanner) {
        String input = "";
        int stringToInt = 0;
        String stringChecked = "";

        while (exceptionIsActive) {
            System.out.println(Constants.ANSI_YELLOW + question + Constants.ANSI_RESET);
            try {
                input = test ? "" : scanner.nextLine();
                stringChecked = input;
                stringToInt = isInt(input);
                exceptionIsActive = false;
            } catch (InputException e) {
                System.out.println(e.getMessage());
                if (test) throw new InputException("");
            }

        }
        exceptionIsActive = true;
        return stringChecked;
    }


    /**
     * @param question
     * @param test
     * @param scanner
     * @return String
     * @Description this method assures that the input matches the email format
     */
    public static String checkEmail(String question, boolean test, Scanner scanner) {

        String input = "";
        int stringToInt = 0;
        String stringChecked = "";

        while (exceptionIsActive) {
            System.out.println(Constants.ANSI_YELLOW + question + Constants.ANSI_RESET);
            try {
                input = test ? "" : scanner.nextLine();
                stringChecked = input;

                if (input.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
                )) {
                    exceptionIsActive = false;
                } else {
                    throw new InputException("Invalid email");
                }
            } catch (InputException e) {
                System.out.println(e.getMessage());
                if (test) throw new InputException("");
            }

        }
        exceptionIsActive = true;
        return stringChecked;

    }
}
