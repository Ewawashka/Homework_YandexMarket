package ru.market.yandex;


import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CatalogPopUpMenu;
import pages.LaptopPage;
import steps.AllSteps;

import java.util.List;

/**
 * @author Алейникова Александра
 */
public class Tests  extends BaseTest{
    @Feature("Выполнение задания 1.4 по YandexMarket" )
    @DisplayName("Прохождение чек листа YandexMarket с помощью PO, все проверки в степах")
    @ParameterizedTest(name="{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#setParameters")
    public void yandexMarketTests(String siteLink, String catalogSectionName,
                                  String catalogCategoriesName, String minPrays, String maxPrays,
                                  List<String> manufactures, String givenNumber){
        AllSteps.openBrowserAndSitePage(siteLink,chromeDriver);
        CatalogPopUpMenu catalogPopUpMenu = AllSteps.openCatalog(chromeDriver);
        AllSteps.HoverCursorOverTheSelectionSection(catalogSectionName,catalogPopUpMenu,chromeDriver);
        LaptopPage laptopPage = AllSteps.selectCategories(catalogCategoriesName,catalogPopUpMenu,chromeDriver);
        AllSteps.checkCategoriesName(catalogCategoriesName,laptopPage);
    //    AllSteps.setPrays(minPrays,maxPrays,laptopPage);
    //    AllSteps.selectManufacturer(manufactures,laptopPage);
        // AllSteps.checkNumberOfElementsDisplayed(givenNumber,laptopPage);
    }
}
