package helpers;

import io.qameta.allure.Step;

/**
 * переопределение Assertions что бы видеть отчет в allure также при удачных проходах теста
 */
    public class Assertions {

        @Step("Проверяем что нет ошибки: {message}")
        public static void assertTrue(boolean condition, String message) {
            org.junit.jupiter.api.Assertions.assertTrue(condition, message);
        }
    }

