package by.bsuir.kaziukovich.oop.consoleview;

import by.bsuir.kaziukovich.oop.consoleview.impl.ConsolePagePrinter;
import by.bsuir.kaziukovich.oop.consoleview.impl.ConsoleScanner;
import by.bsuir.kaziukovich.oop.controller.Controller;
import by.bsuir.kaziukovich.oop.controller.ControllerFactory;
import by.bsuir.kaziukovich.oop.controller.ControllerResponse;
import by.bsuir.kaziukovich.oop.controller.ProcessException;
import by.bsuir.kaziukovich.oop.logger.Logger;
import by.bsuir.kaziukovich.oop.logic.command.CommandName;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * Main class for running application in several modes: data loading, logging and main working mode
 */
public class WorkingModesSwitcher {
    /**
     * Unlogging command
     */
    private static final String EXIT_COMMAND = "exit";

    /**
     * Delimiter char for commands
     */
    private static final char DELIMITER_CHAR = ' ';

    /**
     * Current user username
     */
    private static String username;

    /**
     * Load data mode
     */
    private static void loadData() {
        Controller dataController = ControllerFactory.getDataController();
        boolean areBooksLoaded, areUsersLoaded;

        System.out.print("Enter books data load path: ");
        do {
            try {
                dataController.process(null, CommandName.LOAD_BOOKS.toString() + DELIMITER_CHAR
                        + ConsoleScanner.getNonEmptyString());
                areBooksLoaded = true;
            } catch (ProcessException e) {
                areBooksLoaded = false;
                System.out.print("Error loading book data, try again: ");
                Logger.log(e);
            } catch (IllegalArgumentException e) {
                areBooksLoaded = false;
                System.out.print("Wrong input, try again: ");
                Logger.log(e);
            }
        } while (!areBooksLoaded);

        System.out.print("Enter users data load path: ");
        do {
            try {
                dataController.process(null, CommandName.LOAD_USERS.toString() + DELIMITER_CHAR
                        + ConsoleScanner.getNonEmptyString());
                areUsersLoaded = true;
            } catch (ProcessException e) {
                areUsersLoaded = false;
                System.out.print("Error loading users data, try again: ");
                Logger.log(e);
            } catch (IllegalArgumentException e) {
                areUsersLoaded = false;
                System.out.print("Wrong input, try again: ");
                Logger.log(e);
            }
        } while (!areUsersLoaded);
    }

    /**
     * Logging mode
     */
    private static void login() {
        Controller loginController = ControllerFactory.getLoginController();
        boolean isLogined;
        String enteredUsername, enteredPassword;

        do {
            System.out.print("Enter your name: ");
            enteredUsername = ConsoleScanner.getNonEmptyString();
            System.out.print("Enter your password: ");
            enteredPassword = ConsoleScanner.getNonEmptyString();
            try {
                isLogined = loginController.process(enteredUsername, enteredPassword)[0]
                        .equals(ControllerResponse.SUCCESS_RESPONSE);
                if (!isLogined) {
                    System.out.println("Wrong password, try again: ");
                }
            } catch (ProcessException e) {
                isLogined = false;
                System.out.println("Error while login, try again.");
                Logger.log(e);
            } catch (IllegalArgumentException e) {
                isLogined = false;
                System.out.println("Wrong input, try again: ");
                Logger.log(e);
            }
        } while (!isLogined);
        username = enteredUsername;
    }

    /**
     * Main mode
     */
    private static void work() {
        boolean shouldWork = true;
        Controller usingController = ControllerFactory.getUsingController();
        String input;
        String[] response;

        do {
            System.out.print("Enter your command: ");
            input = ConsoleScanner.getNonEmptyString();
            if (input.equals(EXIT_COMMAND)) {
                shouldWork = false;
            } else {
                try {
                    response = usingController.process(username, input);
                    if (response.length > 1) {
                        ConsolePagePrinter.printByPages(Arrays.copyOfRange(response, 1, response.length));
                    }
                } catch (ProcessException e) {
                    System.out.println("Error processing request, try again");
                    Logger.log(e);
                } catch (IllegalArgumentException e) {
                    System.out.println("Wrong input, try again");
                    Logger.log(e);
                }
            }
        } while (shouldWork);
    }

    /**
     * Program entry point
     * @param args CLI arguments, not in use
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                ConsolePagePrinter.setLinesCount(Byte.parseByte(args[0]));
            } catch (IllegalArgumentException e) {
                System.out.println("Cannot set " + args[0] + " as console lines count, using default value");
            }
        }

        if (args.length > 1) {
            try {
                Logger.setErrorStream(new PrintStream(args[1]));
            } catch (FileNotFoundException | SecurityException e) {
                System.out.println("Cannot set " + args[1] + " as logger stream, using default value");
            }
        }

        loadData();
        while (true) {
            login();
            work();
        }
    }

    /**
     * Private constructor to avoid object creation
     */
    private WorkingModesSwitcher() { }
}
