package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
/**
 * Абстрактный базовый класс для всех объектов страницы в тесте.
 *   <p>Этот класс предоставляет общий конструктор для всех объектов страницы,
 *  принимая экземпляр WebDriver в качестве входных данных и инициализируя
 *  объект FluentWait для ожидания элементов.</p>
 * @author Алейникова Александра
 */
public class AbstractPage {

    /**
     * Экземпляр WebDriver, используемый для взаимодействия с веб-страницей.
     */
    protected WebDriver driver;
    /**
     * Объект ожидания FluentWait, используемый для ожидания доступности элементов на веб-странице.
     *
     * <p>Данный объект ожидания настроен на следующие параметры:</p>
     * <ul>
     *   <li>Время ожидания: 30 секунд</li>
     *   <li>Интервал опроса: 5 секунд</li>
     *   <li>Игнорируемое исключение: NoSuchElementException</li>
     * </ul>
     */
    protected Wait<WebDriver> waitElement;

    /**
     * Конструктор для класса AbstractPage.
     *
     * <p>Этот конструктор принимает экземпляр WebDriver в качестве входных
     * данных и инициализирует объект FluentWait с настроенными параметрами
     * ожидания.</p>
     *
     * @param driver Экземпляр WebDriver, используемый для взаимодействия с веб-страницей.
     */
    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        waitElement = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

    }
    /**
     * Создает новый объект Actions для выполнения действий с клавиатурой и мышью на веб-странице.
     *
     * <p>Этот метод позволяет вам создавать цепочки действий с помощью класса Actions
     * для выполнения сложных взаимодействий с элементами на веб-странице.</p>
     *
     * @return Новый объект Actions, связанный с текущим экземпляром WebDriver.
     */
    public Actions actions() {
        return new Actions(driver);
    }

}
