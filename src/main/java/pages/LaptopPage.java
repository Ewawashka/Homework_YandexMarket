package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Класс LaptopPage наследуется от класса AbstractPage и представляет собой страницу с ноутбуками.
 *
 * @author Алейникова Александра
 */
public class LaptopPage extends AbstractPage {
    /**
     * Элемент поля минимальной цены.
     */
    protected WebElement minPrays;

    /**
     * Элемент поля максимальной цены.
     */
    protected WebElement maxPrays;

    /**
     * Элемент радиокнопки выбора производителя.
     */
    protected WebElement manufacturerRadioButton;

    /**
     * Список ссылок на страницы с ноутбуками.
     */
    protected List<String> laptopPagesUrls = new ArrayList<>();

    /**
     * Xpath-выражение для всех товаров на первой странице.
     */
    protected String allProductsOnPageXpath;

    /**
     * Элемент поля поиска по названию.
     */
    protected WebElement searchField;

    /**
     * Элемент кнопки поиска.
     */
    protected WebElement searchButton;

    /**
     * Конструктор класса LaptopPage.
     *
     * @param driver Экземпляр WebDriver, используемый для взаимодействия со страницей.
     */
    public LaptopPage(WebDriver driver) {
        super(driver);
    }
    /**
     * Получает заголовок страницы.
     *
     * @param searchTitleName Ожидаемое название заголовка (используется для проверки).
     * @return Текст заголовка страницы.
     */
    public String getTitleName(String searchTitleName) {
        return driver.findElement(By.xpath("//h1[@data-auto='title']")).getText();
    }
    /**
     * Устанавливает минимальную цену.
     *
     * @param minNumber Строка с минимальным значением цены.
     */
    public void setMinPrays(String minNumber) {
        minPrays = driver.findElement(By.xpath("//input[contains(@id,'range-filter-field-glprice') and contains(@id,'min')]"));
        minPrays.sendKeys(minNumber);

    }
    /**
     * Устанавливает максимальную цену.
     *
     * @param maxNumber Строка с максимальным значением цены.
     */
    public void setMaxPrays(String maxNumber) {
        maxPrays = driver.findElement(By.xpath("//input[contains(@id,'range-filter-field-glprice') and contains(@id,'max')]"));
        maxPrays.sendKeys(maxNumber);

    }
    /**
     * Устанавливает фильтр по производителю.
     *
     * @param manufacturer Список названий производителей для фильтрации.
     */
    public void setManufacturer(List<String> manufacturer) {

        for (String s : manufacturer) {
            manufacturerRadioButton = driver.findElement(By.xpath("//div[contains(@data-zone-data,'Производитель')]//fieldset//div[contains(@data-zone-data,'" + s + "')]"));
            manufacturerRadioButton.click();
        }

    }
    /**
     * Считает количество товаров на первой странице.
     *
     * @return Количество товаров на первой странице.
     */
    public int findAllProductsOnFirstPage() {
        waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='/marketfrontSerpLayout']//div/article[@data-auto='searchOrganic']")));
        allProductsOnPageXpath = "//div[@id='/marketfrontSerpLayout']//div/article[@data-auto='searchOrganic']";
        List<WebElement> allSelectedProductsOnFirstPage = driver.
                findElements(By.xpath(allProductsOnPageXpath));
        System.out.println(allSelectedProductsOnFirstPage.size() + " количество ноутбуков на странице");
        return allSelectedProductsOnFirstPage.size();
    }
    /**
     * Проверяет соответствие товаров на странице фильтру по производителю.
     *
     * @param manufacturer Список названий производителей для фильтрации.
     * @return true - если все товары соответствуют фильтру, false - иначе.
     */
    public Boolean manufacturerFilterComplianceCheck(List<String> manufacturer) {

       laptopPagesUrls.add(driver.getCurrentUrl());
        List<WebElement> currentPageProducts;
        Boolean allProductsComply = true;
        int lastListSize = 0;
        for (int i = 0; i < 100; i++) {
            waitForLoad(driver);
            currentPageProducts = driver.findElements(By.xpath(allProductsOnPageXpath + "//h3"));
            int currentProductSize = currentPageProducts.size();

            if (currentProductSize == lastListSize) {
                break;
            }
            for (int j = lastListSize; j < currentProductSize; j++) {

                String elementText = currentPageProducts.get(j).getText();
                boolean found = false;
                for (String manufacturerItem : manufacturer
                ) {
                    if (elementText.toLowerCase().contains(manufacturerItem.toLowerCase(Locale.ROOT))) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    WebElement checkingLaptop = currentPageProducts.get(j).findElement(By.xpath("../.."));
                    checkingLaptop.click();
                    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                    driver.switchTo().window(tabs.get(1));
                    System.out.println(elementText + " заходим на страницу ноутбука не соответствующего фильтру");
                    CheckingLaptopPage checkLaptopDescription = new CheckingLaptopPage(driver);
                    allProductsComply = checkLaptopDescription.searchForTextInTheLaptopDescription(manufacturer);
                    driver.close();
                    driver.switchTo().window(tabs.get(0));

                }
                if (!allProductsComply) {
                    System.out.println("найдено не соответствие фильтру");
                    break;
                }
                lastListSize++;
            }
            scrollToElement(driver,currentPageProducts.get(currentPageProducts.size()-1));
            laptopPagesUrls.add(driver.getCurrentUrl());
            if (!allProductsComply) {
                break;
            }
        }
        return allProductsComply;
    }
    /**
     * Получает название сохраненного ноутбука по его позиции на странице и номеру страницы.
     *
     * @param position Позиция ноутбука на странице.
     * @param pageNumber Номер страницы.
     * @return Текст названия ноутбука.
     */
    public String storedLaptop(int position, int pageNumber){
        driver.get(laptopPagesUrls.get(pageNumber-1));
        List<WebElement> pageProductList = driver.findElements(By.xpath( "//div/article[@data-auto='searchOrganic']//h3"));
        return   pageProductList.get(position-1).getText();

    }
    /**
     * Вводит сохраненное название ноутбука в поле поиска.
     *
     * @param laptopName Название ноутбука для поиска.
     */
    public void enteringAStoredValue(String laptopName){
        searchField = driver.findElement(By.xpath("//input[@id='header-search']"));
        searchField.sendKeys(laptopName);
    }
    /**
     * Нажимает на кнопку поиска.
     */
    public void clickOnSearchField(){
        searchButton = driver.findElement(By.xpath("//button[@data-auto='search-button']"));
        searchButton.click();
    }
    /**
     * Проверяет наличие сохраненного ноутбука на первой странице результатов поиска.
     *
     * @param saveLaptopName Название сохраненного ноутбука.
     * @return true - если ноутбук найден на первой странице, false - иначе.
     */
    public Boolean findProductOnFirstPage(String saveLaptopName){
     return   driver.findElement(By.xpath("//h3[contains(text(),'"+saveLaptopName+"')]")).isDisplayed();
        }
    /**
     * Ожидает полной загрузки страницы.
     *
     * @param driver Экземпляр WebDriver, используемый для взаимодействия со страницей.
     *
     * **Внутренний метод, не предназначен для внешнего использования.**
     */
    void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
    /**
     * Прокручивает страницу до указанного веб-элемента.
     *
     * @param driver Экземпляр WebDriver, используемый для взаимодействия со страницей.
     * @param element Веб-элемент, до которого нужно прокрутить страницу.
     *
     * **Внутренний метод, не предназначен для внешнего использования.**
     */
    void scrollToElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

}
