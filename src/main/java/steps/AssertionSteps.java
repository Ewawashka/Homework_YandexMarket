package steps;

import helpers.Assertions;
import io.qameta.allure.Step;
import pages.NoutbukPage;

public class AssertionSteps {
@Step("Проверяемый элемент содержит поисковое слово {searchText}")
    public static void checkSearchText(String textFromElement, String searchText){
    Assertions assertions;
    Assertions.assertTrue(textFromElement.
            contains(searchText),"Элемент с текстом: "+textFromElement+" содержит необходимое слово "+searchText);
}
@Step("Количество отображаемых элементов на странице больше чем {givenNumber} ")
    public static void checkCountSearchingElements(String givenNumber, int realCountOfElements){
    Assertions assertions;
    Assertions.assertTrue(Integer.parseInt(givenNumber) < realCountOfElements,"На первой странице отображается "+realCountOfElements +" элементов товаров.");
}
}
