package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class AbstractPage {
    protected WebDriver driver;
    protected Wait<WebDriver> waitElement;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        waitElement = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    public Actions actions() {
        return new Actions(driver);
    }

}
