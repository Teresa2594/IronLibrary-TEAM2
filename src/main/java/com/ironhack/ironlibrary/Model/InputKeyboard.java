package com.ironhack.ironlibrary.Model;

import java.util.Scanner;

public class InputKeyboard {

   final Scanner scanner = new Scanner (System.in);

    static void isString(String name) throws InputException {
        if(name.matches("[+-]?\\d*(\\.\\d+)?") || name.length()==0 || name.equals(" ")) {
            throw new InputException(Constants.ANSI_RED+"ERROR"+Constants.ANSI_RESET+" : Input not valid");
        }
    }

    static int isInt(String name) throws InputException {
        try{
            return Integer.parseInt(name);
        }catch(NumberFormatException e){
            throw new InputException(Constants.ANSI_RED+"ERROR"+Constants.ANSI_RESET+" : Input not valid");
        }
    }

    public static String checkString(String question, boolean test, Scanner scanner) {
        String name="";
        while (exceptionIsActive) {
            System.out.println(Constants.ANSI_YELLOW+question+Constants.ANSI_RESET);
            try {
                name = test? " ": scanner.nextLine();
                isString(name);
                exceptionIsActive=false;
            } catch (InputException e) {
                System.out.println(e.getMessage()+"\n");
                if(test) throw new InputException("");
            }
        }
        setExceptionIsActive(true);
        return name;
    }

    public static int checkInt(String question, boolean test,Scanner scanner) {
        String input="";
        int stringToInt = 0;
        while (exceptionIsActive) {
            System.out.println(Constants.ANSI_YELLOW+question+Constants.ANSI_RESET);
            try {
                input = test? "": scanner.nextLine();
                stringToInt = isInt(input);
                exceptionIsActive=false;
            } catch (InputException e) {
                System.out.println(e.getMessage());
                if(test) throw new InputException("");
            }

        }
        setExceptionIsActive(true);
        return stringToInt;
    }

    public static String checkFakeInt(String question, boolean test,Scanner scanner) {
        String input="";
        int stringToInt = 0;
        String stringChecked="";

        while (exceptionIsActive) {
            System.out.println(Constants.ANSI_YELLOW+question+Constants.ANSI_RESET);
            try {
                input = test? "": scanner.nextLine();
                stringChecked=input;
                stringToInt = isInt(input);
                exceptionIsActive=false;
            } catch (InputException e) {
                System.out.println(e.getMessage());
                if(test) throw new InputException("");
            }

        }
        setExceptionIsActive(true);
        return stringChecked;
    }


    public static boolean isExceptionIsActive() {
        return exceptionIsActive;
    }

    public static void setExceptionIsActive(boolean exceptionIsActive) {
        InputKeyboard.exceptionIsActive = exceptionIsActive;
    }

    private static boolean exceptionIsActive = true;
}
