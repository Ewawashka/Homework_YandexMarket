package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Locale;

import static org.openqa.selenium.By.xpath;

/**
 * @author Алейникова Александра
 */
public class CheckingLaptopPage extends AbstractPage {
    protected WebElement categoryButton;

    public CheckingLaptopPage(WebDriver driver) {
        super(driver);
    }

    public Boolean searchForTextInTheLaptopDescription(List<String> manufacturer) {
        waitElement.until(ExpectedConditions.visibilityOfElementLocated(xpath("//a[contains(text(),'Все товары')]")));
        categoryButton = driver.findElement(xpath("//a[contains(text(),'Все товары')]"));


        for (String s : manufacturer) {
            if (categoryButton.getText().toLowerCase().contains(s.toLowerCase(Locale.ROOT))) {

                return true;

            }
        }
        return false;
    }
}
