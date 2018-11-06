package by.bsuir.kaziukovich.oop.controller;

import by.bsuir.kaziukovich.oop.controller.data.impl.DataController;
import by.bsuir.kaziukovich.oop.controller.login.impl.LoginController;
import by.bsuir.kaziukovich.oop.controller.using.impl.UsingController;

/**
 * Controller instances factory
 */
public class ControllerFactory {
    /**
     * Single data controller instance
     */
    private static Controller dataController;

    /**
     * Single login controller instance
     */
    private static Controller loginController;

    /**
     * Single using controller instance
     */
    private static Controller usingController;

    /**
     * Generates instance of data controller
     * @return Instance of data controller
     */
    public static Controller getDataController() {
        if (dataController == null) {
            dataController = new DataController();
        }
        return dataController;
    }

    /**
     * Generates instance of login controller
     * @return Instance of login controller
     */
    public static Controller getLoginController() {
        if (loginController == null) {
            loginController = new LoginController();
        }
        return loginController;
    }

    /**
     * Generates instance of using controller
     * @return Instance of using controller
     */
    public static Controller getUsingController() {
        if (usingController == null) {
            usingController = new UsingController();
        }
        return usingController;
    }

    /**
     * Private constructor to avoid object creation
     */
    private ControllerFactory() { }
}
