package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DataProvider {
    public static Stream<Arguments> setParameters(){
        List<String> manufactures = new ArrayList<>();
        manufactures.add("Lenovo");
        manufactures.add("HP");
        return Stream.of(
                Arguments.of("https://market.yandex.ru/","Ноутбуки и компьютеры","Ноутбуки","10000","30000",manufactures,"12")
        );
    }
}
