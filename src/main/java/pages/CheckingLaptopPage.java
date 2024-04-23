package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
/**
 * @author Алейникова Александра
 */
public class CheckingLaptopPage extends AbstractPage {
    protected WebElement description;
    public CheckingLaptopPage(WebDriver driver) {
        super(driver);
    }
    public Boolean searchForTextInTheLaptopDescription(List<String> manufacturer){

        description = driver.findElement(By.xpath("//h2[contains(text(),'Описание')]/../../span"));
        Boolean found = false;
        for (int i = 0; i <manufacturer.size(); i++) {
            if(description.getText().contains(manufacturer.get(i))){
                found = true;
                break;
            }
        }
        return found;
    }
}
