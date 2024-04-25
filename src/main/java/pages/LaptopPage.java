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
 * @author Алейникова Александра
 */
public class LaptopPage extends AbstractPage {
    protected WebElement minPrays;
    protected WebElement maxPrays;
    protected WebElement manufacturerRadioButton;
    protected List <String> laptopPagesUrls = new ArrayList<>();
    protected String allProductsOnPageXpath;
    protected WebElement searchField;
    protected WebElement searchButton;

    public LaptopPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleName(String searchTitleName) {
        return driver.findElement(By.xpath("//h1[@data-auto='title']")).getText();
    }

    public void setMinPrays(String minNumber) {
        minPrays = driver.findElement(By.xpath("//input[contains(@id,'range-filter-field-glprice') and contains(@id,'min')]"));
        minPrays.sendKeys(minNumber);

    }

    public void setMaxPrays(String maxNumber) {
        maxPrays = driver.findElement(By.xpath("//input[contains(@id,'range-filter-field-glprice') and contains(@id,'max')]"));
        maxPrays.sendKeys(maxNumber);

    }

    public void setManufacturer(List<String> manufacturer) {

        for (String s : manufacturer) {
            manufacturerRadioButton = driver.findElement(By.xpath("//div[contains(@data-zone-data,'Производитель')]//fieldset//div[contains(@data-zone-data,'" + s + "')]"));
            manufacturerRadioButton.click();
        }

    }

    public int findAllProductsOnFirstPage() {
        waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='/marketfrontSerpLayout']//div/article[@data-auto='searchOrganic']")));
        allProductsOnPageXpath = "//div[@id='/marketfrontSerpLayout']//div/article[@data-auto='searchOrganic']";
        List<WebElement> allSelectedProductsOnFirstPage = driver.
                findElements(By.xpath(allProductsOnPageXpath));
        System.out.println(allSelectedProductsOnFirstPage.size() + "количество ноутбуков на странице"); //  убрать потом
        return allSelectedProductsOnFirstPage.size();
    }

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
    public String storedLaptop(int position, int pageNumber){
        driver.get(laptopPagesUrls.get(pageNumber-1));
        return   driver.findElements(By.xpath(allProductsOnPageXpath + "//h3")).get(position-1).getText();

    }
    public void enteringAStoredValue(String laptopName){
        searchField = driver.findElement(By.xpath("//input[@id='header-search']"));
        searchField.sendKeys(laptopName);
    }
    public void clickOnSearchField(){
        searchButton = driver.findElement(By.xpath("//button[@data-auto='search-button']"));
        searchButton.click();
    }
    public Boolean findProductOnFirstPage(String saveLaptopName){
     return   driver.findElement(By.xpath("//h3[contains(text(),'"+saveLaptopName+"')]")).isDisplayed();
        }



    void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
    void scrollToElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

}
