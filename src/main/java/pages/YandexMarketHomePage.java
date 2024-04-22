package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;


/**
 * класс домашней страницы Яндекс маркета
 */
public class YandexMarketHomePage extends AbstractPage {
    /**
     * кнопка каталога на домашней странице
     */

    protected WebElement catalogButton;

    public YandexMarketHomePage(WebDriver driver) {
        super(driver);
        waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-baobab-name='catalog']")));
        catalogButton = driver.findElement(By.xpath("//*[contains(text(),'Каталог')]/.."));
    }

    /**
     * кликаем на кнопку каталога
     * @return объект страницы каталога
     */
    public CatalogPopUpMenu clickOnCatalogButton(){

        catalogButton.click();
        return new CatalogPopUpMenu(driver);
    }
}
