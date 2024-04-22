package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * класс всплывающего окна каталога
 */
public class CatalogPopUpMenu extends AbstractPage{
    protected WebElement catalogSelectionSection;
    protected WebElement catalogSelectionCategories;
    public CatalogPopUpMenu(WebDriver driver) {
        super(driver);
    }

    /**
     * метод наведения курсора на элемент.
     * @param elementName передаваемое название элемента с которым нужно совершить действие
     * @return
     */
    public CatalogPopUpMenu hoverCursor(String elementName){
        String searchElementXpath = "//span[contains(text(),'"+ elementName + "')]/..";
        String finalElementXpath = "//span[contains(text(),'Уценка')]/..";
        scrollForElement(searchElementXpath);
        return this;
    }
    public CatalogPopUpMenu scrollForElement(String searchElementXpath){
        WebElement element = null;
        for (int i = 0; i < 3; i++) {
            System.out.println("scrollForElement try"+i); //удалить
            waitForLoad(driver);
            String lastElementXpath = searchElementXpath + "/../../..//li[last()]";
            waitElement.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lastElementXpath)));
            WebElement lastElement = driver.findElement(By.xpath(lastElementXpath));
            actions().moveToElement(lastElement);
            waitForLoad(driver);
            waitElement.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchElementXpath)));
                 element = driver.findElement(By.xpath(searchElementXpath));
                actions().moveToElement(element).perform();

                if (driver.findElement(By.xpath(searchElementXpath)).isDisplayed()) {
                    break;
                }

        }
        return this;
    }
    public NoutbukPage clickOnElement(String elementName){
        catalogSelectionCategories = driver.findElement(By.xpath("//div[@aria-level='2']//a[text() = 'Ноутбуки']"));
        actions().moveToElement(catalogSelectionCategories).perform();
        catalogSelectionCategories.click();
        return new NoutbukPage(driver);
    }
    void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
    private WebElement findLastListElement(String elementName){
        return driver.findElement(By.xpath("//span[contains(text(),'"+ elementName + "')]/../../../..//li[last()]"));
    }

}
