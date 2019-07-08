package pflb.testWork.awesomeBddWithCucumber.Runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WDriver {

    private static ThreadLocal<WDriver> driver = new ThreadLocal<>();

    private WebDriver webDriver;
    private WebDriverWait wait;
    private String browser = "";


    WDriver() {
        browser = Runner.browserName.get();
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "src/test/resources/WebDrivers/chromedriver.exe");
            //создание нового объекта ChromeDriver
            webDriver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    "src/test/resources/WebDrivers/geckodriver.exe");
            webDriver = new FirefoxDriver();
        }

        //разворачиваем окно браузера на полный экран
        webDriver.manage().window().maximize();
//            инициализируем объект ожидания для условного поиска элементов.
//            параметры конструктора: экземпляр веб драйвера, полное время (таймаут), частота проверки
        wait = new WebDriverWait(webDriver, 10, 250);
    }

    /**
     * Метод для получения экземпляра класса обертки.
     * См. паттерн Синглтон.
     *
     * @return
     */
    public static WDriver getInstance() {
        if (driver.get() == null) {
            driver.set(new WDriver());
        }
        return driver.get();
    }

    private static final Logger log = LogManager.getLogger();

    /**
     * Расширенный метод findElement.
     * Проверяет:
     * 1) присутствие в HTML дереве
     * 2) виден ли элемент на экране
     * 3) кликабелен ли элемент
     *
     * @param xpath xpath
     * @return элемент
     */
    public WebElement findElement(String xpath) {
        WebElement e;
        try {
            //проверяем, присутствует ли элемент в HTML документе
            e = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            //проверяем, виден ли элемент на экране
            e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            //проверяем, является ли элемент кликабельным
            e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception exception) {
            log.error("Не смог найти элемент с локатором '{}'.", xpath);
            throw new RuntimeException();
        }
        log.debug("Элемент по xpath локатору '{}' найден.", xpath);
        return e;
    }


    /**
     * Метод для клика по элементу
     *
     * @param xpath xpath
     */
    public void click(String xpath) {
        WebElement e = findElement(xpath);
        e.click();
        log.debug("Кликнул элемент.");
    }

    /**
     * Метод для ввода данных на элемент (пример - ввод данных в текстовое поле)
     *
     * @param xpath     xpath
     * @param textValue текст для ввода
     */
    public void sendKeys(String xpath, String textValue) {
        WebElement e = findElement(xpath);
        e.sendKeys(textValue);
        log.debug("Ввел значение '{}' в поле '{}'.", textValue, xpath);
    }

    /**
     * Метод для открытия веб-страницы
     *
     * @param url адресс
     */

    public void get(String url) {
        webDriver.get(url);
        log.debug("Открываем страницу '{}'.", url);
    }

    /**
     * Метод для закрытия текущей вкладки
     */

    public void close() {
        webDriver.close();
        log.debug("Закрыл вкладку.");
    }

}
