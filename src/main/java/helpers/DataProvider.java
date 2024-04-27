package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Класс DataProvider предназначен для предоставления тестовых данных
 * для использования в методах тестов с аннотацией @Test.
 */
public class DataProvider {
    /**
     * Этот статический метод возвращает поток аргументов (Stream<Arguments>),
     * используемый для параметризации тестов.
     *
     * <p>Метод создает список производителей ноутбуков (manufactures)
     * и возвращает поток, содержащий один набор тестовых данных.</p>
     *
     * <p>Каждый набор тестовых данных представлен объектом Arguments и
     * содержит следующие аргументы:</p>
     * <ul>
     *   <li>URL-адрес сайта (String)</li>
     *   <li>Заголовок главной категории (String)</li>
     *   <li>Заголовок подкатегории (String)</li>
     *   <li>Минимальная цена (String)</li>
     *   <li>Максимальная цена (String)</li>
     *   <li>Список производителей ноутбуков (List<String>)</li>
     *   <li>Запрашиваемое количество результатов на странице (String)</li>
     *   <li>Номер запрашиваемой страницы ноутбуков(String)</li>
     *   <li>Номер сохраняемого ноутбука(String)</li>
     * </ul>
     *
     * @return Поток аргументов (Stream<Arguments>),
     *         содержащий один набор тестовых данных.
     */
    public static Stream<Arguments> setParameters(){
        List<String> manufactures = new ArrayList<>();
        manufactures.add("Lenovo");
        manufactures.add("HP");
        return Stream.of(
                Arguments.of("Ноутбуки и компьютеры","Ноутбуки","10000","30000",manufactures,"12","1","1")
        );
    }
}
