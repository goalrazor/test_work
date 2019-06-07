package stepdefs;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.collections.Pair;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WDriver {

    private static final Logger log = LogManager.getLogger();
    private static Properties property = new Properties();
    /**
     * статическое поле, которое хранит экземпляр Selenium Driver для браузера Chrome
     */
    private ChromeDriver chromeDriver;

    /**
     * статическое поле, которое хранит единственный экземпляр нашего класса-обертки,
     * используется в паттерне Синглтон.
     */
    private static WDriver driver;

    /**
     * объект для работы с ожиданиями
     */
    private WebDriverWait wait;

    /**
     * Конструктор класса-обертки. Модификатор конструктора private
     * не позволяет вызывать конструктор нашего класса. Единственный способ получения
     * экземпляра класса-обертки - метод getInstance().
     */
    WDriver() {
        //установка пути до исполняемого файла WebDriver (chromedriver.exe)
        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/WebDrivers/chromedriver");
        //создание нового объекта ChromeDriver
        chromeDriver = new ChromeDriver();
        //разворачиваем окно браузера Chrome на полный экран
        chromeDriver.manage().window().maximize();
        //инициализируем объект ожидания для условного поиска элементов.
        //параметры конструктора: экземпляр веб драйвера, полное время (таймаут), частота проверки
        wait = new WebDriverWait(chromeDriver, 10, 250);
    }

    /**
     * Метод для получения экземпляра класса обертки.
     * См. паттерн Синглтон.
     *
     * @return экземпляр драйвера
     */
    public static WDriver getInstance() {
        if (driver == null) {
            driver = new WDriver();
        }
        return driver;
    }

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
        WebElement e = driver.findElement(xpath);
        e.click();
        log.debug("Кликнул элемент.");
    }

    /**
     * Метод для ввода данных на элемент (пример - ввод данных в текстовое поле)
     *
     * @param xpath xpath
     * @param textValue текст для ввода
     */
    public void sendKeys(String xpath, String textValue) {
        WebElement e = driver.findElement(xpath);
        e.sendKeys(textValue);
        log.debug("Ввел значение '{}' в поле '{}'.", textValue, xpath);
    }

    /**
     * Метод для открытия веб-страницы
     *
     * @param url адресс
     */
    public void get(String url) {
        chromeDriver.get(url);
        log.debug("Открываем страницу '{}'.", url);
    }

    /**
     * Метод для закрытия текущей вкладки
     */
    public void close() {
        chromeDriver.close();
        log.debug("Закрыл вкладку.");
    }

    /**
     * Возвратить аккаунт-пару для пользователя
     *
     * @return Пара - имя/пароль пользователя
     */
    static Pair getAccountPair() {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/gmail.properties");
            property.load(fis);
            return Pair.create(property.getProperty("login"), property.getProperty("password"));
        } catch (IOException e) {
            log.error("ОШИБКА: Файл свойств отсуствует!");
        }
        return null;
    }

    public static String getProperty(String key) {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/gmail.properties");
            property.load(fis);
            return property.getProperty(key);
        } catch (IOException e) {
            log.error("ОШИБКА: Файл свойств отсуствует!");
        }
        return null;
    }

    public String getUrl() {
        String currentUrl = chromeDriver.getCurrentUrl();
        log.debug("Мы находимся на странице с URL '{}'", currentUrl);
        return currentUrl;
    }

}
