package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;



/**
 * Класс YandexMarketHomePage представляет собой домашнюю страницу Яндекс.Маркета.
 * Он наследуется от класса AbstractPage и предоставляет методы для взаимодействия
 * с элементами домашней страницы.
 * @author  Александра Алейникова
 */

public class YandexMarketHomePage extends AbstractPage {

    /**
     * WebElement, представляющий кнопку "Каталог" на главной странице.
     */
    protected WebElement catalogButton;
    /**
     * Конструктор класса YandexMarketHomePage.
     *
     * <p>Принимает экземпляр WebDriver в качестве входных данных, вызывает
     * конструктор родительского класса AbstractPage и ожидает появления
     * элемента "Каталог" на странице.</p>
     *
     * @param driver Экземпляр WebDriver, используемый для взаимодействия с веб-страницей.
     */
    public YandexMarketHomePage(WebDriver driver) {
        super(driver);
        waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-baobab-name='catalog']")));
        catalogButton = driver.findElement(By.xpath("//*[contains(text(),'Каталог')]/.."));
    }

    /**
     * Кликает по кнопке "Каталог" и возвращает объект CatalogPopUpMenu.
     *
     * <p>Метод находит кнопку "Каталог" с помощью заранее
     * инициализированного поля `catalogButton`, кликает по ней и
     * возвращает новый объект CatalogPopUpMenu, представляющий
     * всплывающее меню каталога.</p>
     *
     * @return Новый объект CatalogPopUpMenu, представляющий всплывающее меню каталога.
     */

    public CatalogPopUpMenu clickOnCatalogButton(){

        catalogButton.click();
        return new CatalogPopUpMenu(driver);
    }
}
