package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CatalogPopUpMenu;
import pages.LaptopPage;
import pages.YandexMarketHomePage;

import java.util.List;

/**
 * @author Алейникова Александра
 */
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
    public static void  HoverCursorOverTheSelectionSection(String sectionName, CatalogPopUpMenu catalogPopUpMenu, WebDriver driver) {
       catalogPopUpMenu.hoverCursor(sectionName);
    }

    @Step("Перейти в раздел {categoriesName}")
    public static  LaptopPage selectCategories(String categoriesName, CatalogPopUpMenu catalogPopUpMenu, WebDriver driver) {
        return catalogPopUpMenu.clickOnElement(categoriesName);
    }

    @Step("Убедится что вы перешли в раздел {categoriesName}")
    public static void  checkCategoriesName(String categoriesName, LaptopPage laptopPage) {
        AssertionSteps.checkSearchText(laptopPage.getTitleName(categoriesName), categoriesName);
    }

    @Step("Задать параметр «Цена, Р» от  {minPrays} до {maxPrays} рублей.")
    public static void setPrays(String minPrays, String maxPrays, LaptopPage laptopPage){
        laptopPage.setMinPrays(minPrays);
        laptopPage.setMaxPrays(maxPrays);

    }
    @Step("Выбрать производителя {manufacturer}")
    public  static void selectManufacturer(List<String> manufacturer, LaptopPage laptopPage){
        laptopPage.setManufacturer(manufacturer);
    }
    @Step("Проверить, что на первой странице отображалось более {givenNumber} элементов товаров.")
    public static void checkNumberOfElementsDisplayed(String givenNumber, LaptopPage laptopPage){
        AssertionSteps.checkCountSearchingElements(givenNumber,laptopPage.findAllProductsOnFirstPage());
    }
    @Step(" Проверить что на всех страницах предложения соответствуют фильтру.")
    public static void isSelectedManufacturesOnAllProductPage(List<String> manufacturer, LaptopPage laptopPage){
        AssertionSteps.checkingIfOffersMatchFilters(laptopPage.manufacturerFilterComplianceCheck(manufacturer));
}
    @Step("Вернуться на {laptopPageNumber} страницу с результатами поиска ноутбуков и запомнить {laptopNumber} наименование ноутбука")
    public static String rememberFirstLaptopOnFirstPage(LaptopPage laptopPage,String laptopPageNumber,String laptopNumber){
    return   laptopPage.storedLaptop(Integer.parseInt(laptopPageNumber),Integer.parseInt(laptopNumber));
    }
    @Step("В поисковую строку ввести запомненное значение.")
    public static void enteringAStoredValue(LaptopPage laptopPage, String laptopName){
        laptopPage.enteringAStoredValue(laptopName);
    }
    @Step("Нажать кнопку «Найти»")
    public static void clickOnSearchButton(LaptopPage laptopPage){
        laptopPage.clickOnSearchField();
    }
    @Step("Проверить, что в результатах поиска, на первой странице, есть искомый товар")
    public static void  isProductPresentOnFirstPage(LaptopPage laptopPage, String saveLaptopName){
        AssertionSteps.checkingSearchResults(laptopPage.findProductOnFirstPage(saveLaptopName));
    }
}


