package steps;

import helpers.Assertions;
import io.qameta.allure.Step;

import java.util.List;

/**
 * @author Алейникова Александра
 */
public class AssertionSteps {
@Step("Проверяемый элемент содержит поисковое слово {searchText}")
    public static void checkSearchText(String textFromElement, String searchText){
    Assertions.assertTrue(textFromElement.
            contains(searchText),"Элемент с текстом: "+textFromElement+" содержит необходимое слово "+searchText);
}
@Step("Количество отображаемых элементов на странице больше чем {givenNumber} ")
    public static void checkCountSearchingElements(String givenNumber, int realCountOfElements){
    Assertions.assertTrue(Integer.parseInt(givenNumber) < realCountOfElements,"На первой странице отображается "+realCountOfElements +" элементов товаров.");
}
@Step ("На всех страницах маркета предложения соответствуют выставленным фильтрам{List<String> manufacturer}")
    public static void checkingIfOffersMatchFilters( Boolean manufacturerFilterComplianceCheck){
    Boolean defaultCondition = true;
    Assertions.assertTrue(defaultCondition==manufacturerFilterComplianceCheck,"Предложения на все страницах соответствуют фильтру");
}
}
