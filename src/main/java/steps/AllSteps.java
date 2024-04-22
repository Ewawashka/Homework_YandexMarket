package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CatalogPopUpMenu;
import pages.NoutbukPage;
import pages.YandexMarketHomePage;

import java.util.List;

public class AllSteps {
    @Step("Открыть браузер и развернуть на весь экран. Перейти на {siteLink}")
    public static void openBrowserAndSitePage(String siteLink, WebDriver driver) {
        driver.get(siteLink);
    }

    @Step("Перейти в Каталог")
    public static CatalogPopUpMenu openCatalog(WebDriver driver) {
        YandexMarketHomePage yandexMarketHomePage = new YandexMarketHomePage(driver);
        return yandexMarketHomePage.clickOnCatalogButton();
    }

    @Step("Навести курсор на раздел {nameOfSection}")
    public static CatalogPopUpMenu HoverCursorOverTheSelectionSection(String sectionName, CatalogPopUpMenu catalogPopUpMenu, WebDriver driver) {
        CatalogPopUpMenu thisCatalogPopUpMenu = catalogPopUpMenu;
        return thisCatalogPopUpMenu.hoverCursor(sectionName);
    }

    @Step("Перейти в раздел {categoriesName}")
    public static NoutbukPage selectCategories(String categoriesName, CatalogPopUpMenu catalogPopUpMenu, WebDriver driver) {
        CatalogPopUpMenu thisCatalogPopUpMenu = catalogPopUpMenu;
        return thisCatalogPopUpMenu.clickOnElement(categoriesName);
    }

    @Step("Убедится что вы перешли в раздел {categoriesName}")
    public static NoutbukPage checkCategoriesName(String categoriesName, NoutbukPage noutbukPage) {
        NoutbukPage thisNoutbukPage = noutbukPage;
        AssertionSteps.checkSearchText(thisNoutbukPage.getTitleName(categoriesName), categoriesName);
        return thisNoutbukPage;
    }

    @Step("Задать параметр «Цена, Р» от  {minPrays} до {maxPrays} рублей.")
    public static void setPrays(String minPrays, String maxPrays, NoutbukPage noutbukPage){
        NoutbukPage thisNoutbukPage = noutbukPage;
        noutbukPage.setMinPrays(minPrays);
        noutbukPage.setMaxPrays(maxPrays);

    }
    @Step("Выбрать производителя {manufacturer}")
    public  static void selectManufacturer(List<String> manufacturer, NoutbukPage noutbukPage){
        NoutbukPage thisNoutbukPage = noutbukPage;
        thisNoutbukPage.setManufacturer(manufacturer);
    }
    @Step("Проверить, что на первой странице отображалось более {givenNumber} элементов товаров.")
    public static void checkNumberOfElementsDisplayed(String givenNumber,NoutbukPage noutbukPage){
        AssertionSteps.checkCountSearchingElements(givenNumber,noutbukPage.findAllProductsOnFirstPage());
    }
    @Step(" Проверить что на всех страницах предложения соответствуют фильтру.")
    public static void isSelectedManufacturesOnAllProductPage(List<String> manufacturer, NoutbukPage noutbukPage){

}

}


