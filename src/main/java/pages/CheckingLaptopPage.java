package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Locale;

import static org.openqa.selenium.By.xpath;

/**
 * Класс CheckingLaptopPage наследуется от класса AbstractPage и представляет собой страницу проверки ноутбуков.
 *
 * @author Алейникова Александра
 */
public class CheckingLaptopPage extends AbstractPage {
    /**
     * Элемент кнопки "все товары производителя" на странице ноутбука.
     */
    protected WebElement allProductOfManufacturer;
    /**
     * Конструктор класса CheckingLaptopPage.
     *
     * @param driver Экземпляр WebDriver, используемый для взаимодействия со страницей.
     */
    public CheckingLaptopPage(WebDriver driver) {
        super(driver);
    }
    /**
     * Метод проверяет наличие текста производителя ноутбука в описании страницы.
     *
     * @param manufacturer Список названий производителей ноутбуков для поиска (строчными буквами).
     * @return true - если хотя бы один производитель из списка найден в описании страницы, false - иначе.
     */
    public Boolean searchForTextInTheLaptopDescription(List<String> manufacturer) {
        waitElement.until(ExpectedConditions.visibilityOfElementLocated(xpath("//a[contains(text(),'Все товары')]")));
        allProductOfManufacturer = driver.findElement(xpath("//a[contains(text(),'Все товары')]"));


        for (String s : manufacturer) {
            if (allProductOfManufacturer.getText().toLowerCase().contains(s.toLowerCase(Locale.ROOT))) {

                return true;

            }
        }
        return false;
    }
}
