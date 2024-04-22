package ru.market.yandex;


import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CatalogPopUpMenu;
import pages.NoutbukPage;
import steps.AllSteps;

import java.util.List;

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
        catalogPopUpMenu =  AllSteps.HoverCursorOverTheSelectionSection(catalogSectionName,catalogPopUpMenu,chromeDriver);
        NoutbukPage noutbukPage = AllSteps.selectCategories(catalogCategoriesName,catalogPopUpMenu,chromeDriver);
        AllSteps.checkCategoriesName(catalogCategoriesName,noutbukPage);
    //    AllSteps.setPrays(minPrays,maxPrays,noutbukPage);
    //    AllSteps.selectManufacturer(manufactures,noutbukPage);
        // AllSteps.checkNumberOfElementsDisplayed(givenNumber,noutbukPage);
    }
}
