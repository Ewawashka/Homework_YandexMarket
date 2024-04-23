package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Класс CatalogPopUpMenu представляет собой компонент всплывающего меню каталога
 * на веб-странице. Он наследуется от класса AbstractPage и предоставляет
 * методы для взаимодействия с элементами меню.
 * @author Алейникова Александра
 */
public class CatalogPopUpMenu extends AbstractPage{
    /**
     * WebElement, представляющий раздел выбора каталога во всплывающем меню.
     */
    protected WebElement catalogSelectionSection;
    /**
     * WebElement, представляющий категории выбора во всплывающем меню каталога
     * (может потребоваться инициализация).
     */
    protected WebElement catalogSelectionCategories;
    /**
     * Конструктор класса CatalogPopUpMenu.
     *
     * <p>Принимает экземпляр WebDriver в качестве входных данных и вызывает
     * конструктор родительского класса AbstractPage.</p>
     *
     * @param driver Экземпляр WebDriver, используемый для взаимодействия с веб-страницей.
     */
    public CatalogPopUpMenu(WebDriver driver) {
        super(driver);
    }
    /**
     * Наводит курсор на элемент во всплывающем меню каталога по его имени.
     *
     * <p>Данный метод ожидает загрузки страницы, проверяет наличие элемента
     * и затем наводит на него курсор.</p>
     *
     * @param elementName Имя элемента во всплывающем меню каталога (например, "Ноутбуки и компьютеры").
     */
    public void hoverCursor(String elementName){
        String searchElementXpath = "//span[contains(text(),'"+ elementName + "')]/..";
         //   waitForLoad(driver);
            waitElement.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchElementXpath)));
        WebElement element = driver.findElement(By.xpath(searchElementXpath));
                actions().moveToElement(element).perform();
        }

    /**
     * Кликает по элементу во всплывающем меню каталога по его имени
     * и возвращает новую страницу ноутбуков.
     *
     * <p>Метод находит элемент "Ноутбуки" во всплывающем меню,
     * наводит на него курсор и затем кликает.
     * После этого он создает и возвращает новый объект LaptopPage.</p>
     *
     * @return Новый объект LaptopPage, представляющий страницу ноутбуков.
     */
    public LaptopPage clickOnElement(String elementName){
        catalogSelectionCategories = driver.findElement(By.xpath("//div[@aria-level='2']//a[text() = 'Ноутбуки']"));
        actions().moveToElement(catalogSelectionCategories).perform();
        catalogSelectionCategories.click();
        return new LaptopPage(driver);
    }
//    /**
//     * Метод ожидает полной загрузки страницы (недоступен извне класса).
//     *
//     * <p>Этот метод ожидает 30 секунд, пока свойство `readyState`
//     * документа не станет равно "complete", используя JavascriptExecutor.</p>
//     *
//     * @param driver Экземпляр WebDriver, используемый для взаимодействия с веб-страницей.
//     */
//    void waitForLoad(WebDriver driver) {
//        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
//                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
//    }

}
