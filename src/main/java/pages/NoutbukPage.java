package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class NoutbukPage extends AbstractPage {
    protected WebElement minPrays;
    protected WebElement maxPrays;
    protected WebElement manufacturerRadioButton;

    public NoutbukPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleName(String searchTitleName) {
        return driver.findElement(By.xpath("//h1[@data-auto='title']")).getText();
    }

    public NoutbukPage setMinPrays(String minNumber) {
        minPrays = driver.findElement(By.xpath("//input[contains(@id,'range-filter-field-glprice') and contains(@id,'min')]"));
        minPrays.sendKeys(minNumber);
        return this;
    }

    public NoutbukPage setMaxPrays(String maxNumber) {
        maxPrays = driver.findElement(By.xpath("//input[contains(@id,'range-filter-field-glprice') and contains(@id,'max')]"));
        maxPrays.sendKeys(maxNumber);
        return this;
    }

    public NoutbukPage setManufacturer(List<String> manufacturer) {
        List<String> thisManufacturer = manufacturer;
        for (int i = 0; i < thisManufacturer.size(); i++) {
            manufacturerRadioButton = driver.findElement(By.xpath("//div[contains(@data-zone-data,'Производитель')]//fieldset//div[contains(@data-zone-data,'"+thisManufacturer.get(i)+"')]"));
            manufacturerRadioButton.click();
        }
        return  this;
    }
    public int findAllProductsOnFirstPage(){
       List <WebElement > allSelectedProductsOnFirstPage = new ArrayList<>();
        allSelectedProductsOnFirstPage = driver.findElements(By.xpath("//div[contains(@id,'/marketfrontSerpLayout/') and @data-apiary-widget-name='@marketfront/SerpEntity']//a[@data-auto='snippet-link']//h3[contains(text(),'Ноутбук')]"));
       return  allSelectedProductsOnFirstPage.size();
    }

}
