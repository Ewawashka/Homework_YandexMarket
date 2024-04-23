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

/**
 * @author Алейникова Александра
 */
public class LaptopPage extends AbstractPage {
    protected WebElement minPrays;
    protected WebElement maxPrays;
    protected WebElement manufacturerRadioButton;
    protected String firstLaptopPageUrl;
    protected String allProductsOnPageXpath;

    public LaptopPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleName(String searchTitleName) {
        return driver.findElement(By.xpath("//h1[@data-auto='title']")).getText();
    }

    public LaptopPage setMinPrays(String minNumber) {
        minPrays = driver.findElement(By.xpath("//input[contains(@id,'range-filter-field-glprice') and contains(@id,'min')]"));
        minPrays.sendKeys(minNumber);
        return this;
    }

    public LaptopPage setMaxPrays(String maxNumber) {
        maxPrays = driver.findElement(By.xpath("//input[contains(@id,'range-filter-field-glprice') and contains(@id,'max')]"));
        maxPrays.sendKeys(maxNumber);
        return this;
    }

    public LaptopPage setManufacturer(List<String> manufacturer) {

        for (int i = 0; i < manufacturer.size(); i++) {
            manufacturerRadioButton = driver.findElement(By.xpath("//div[contains(@data-zone-data,'Производитель')]//fieldset//div[contains(@data-zone-data,'" + manufacturer.get(i) + "')]"));
            manufacturerRadioButton.click();
        }
        return this;
    }

    public int findAllProductsOnFirstPage() {
        allProductsOnPageXpath = "//div[@id='/marketfrontSerpLayout']//div/article";
        List<WebElement> allSelectedProductsOnFirstPage = driver.
                findElements(By.xpath(allProductsOnPageXpath));
        System.out.println(allSelectedProductsOnFirstPage.size()+"количество ноутбуков на странице"); //  убрать потом
        return allSelectedProductsOnFirstPage.size();
    }

    public Boolean manufacturerFilterComplianceCheck(List<String> manufacturer) {
        firstLaptopPageUrl = driver.getCurrentUrl();
        String currentUrl = driver.getCurrentUrl();
        List<WebElement> currentPageProducts;
        Boolean allProductsComply = true;
        while (allProductsComply) {
            currentPageProducts = driver.findElements(By.xpath(allProductsOnPageXpath + "//h3"));
            for (int i = 0; i < currentPageProducts.size(); i++) {
                String elementText = currentPageProducts.get(i).getText();
                Boolean found = false;
                for (String manufacturerItem : manufacturer
                ) {
                    if (elementText.contains(manufacturerItem)) {
                        found = true;
                        break;
                    }

                }
                if (!found) {
                    WebElement checkingLaptop = currentPageProducts.get(i).findElement(By.xpath("/.."));
                    checkingLaptop.click();
                    CheckingLaptopPage checkLaptopDescription = new CheckingLaptopPage(driver);
                    allProductsComply = checkLaptopDescription.searchForTextInTheLaptopDescription(manufacturer);
                    if(!allProductsComply){
                        System.out.println("найдено не соответствие фильтру");
                        break;
                    }
                    driver.get(currentUrl);
                }
            }
            currentPageProducts = nextProductPage();
            currentUrl = driver.getCurrentUrl();
        }
        return allProductsComply;
    }

    public List<WebElement> nextProductPage() {
        driver.findElement(By.xpath("//div[@data-baobab-name='next'] ")).click();
        waitForLoad(driver);
        return driver.findElements(By.xpath(allProductsOnPageXpath + "//h3"));

    }
    void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

}
