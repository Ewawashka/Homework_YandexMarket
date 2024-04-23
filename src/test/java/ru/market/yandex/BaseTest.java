package ru.market.yandex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * @author Алейникова Александра
 */
public class BaseTest {
    protected WebDriver chromeDriver;

    @BeforeEach
    public void beforeTests() {
        ChromeOptions options = new ChromeOptions();
        //System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Beach Bum\\Desktop\\YandexWork\\chromedriver-win64\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();

    }

    @AfterEach
    public void afterTests() {
        chromeDriver.quit();

    }
}
