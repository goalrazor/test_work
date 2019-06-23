package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefs"})
public class Runner {

    private TestNGCucumberRunner testNGCucumberRunner;
    private static WebDriverWait wait;


    @BeforeClass(alwaysRun = true)
    public void setUpClass(){
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    public static WebDriver driver;
    @BeforeClass
    @Parameters("browser")
    public void setBrowser(String browser) {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "src/test/resources/WebDrivers/chromedriver.exe");
            //создание нового объекта ChromeDriver
            ChromeDriver chromeDriver = new ChromeDriver();
            //разворачиваем окно браузера Chrome на полный экран
            chromeDriver.manage().window().maximize();
//            инициализируем объект ожидания для условного поиска элементов.
//            параметры конструктора: экземпляр веб драйвера, полное время (таймаут), частота проверки
            wait = new WebDriverWait(chromeDriver, 10, 250);
            driver = chromeDriver;
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    "src/test/resources/WebDrivers/geckodriver.exe");
            FirefoxDriver firefoxDriver = new FirefoxDriver();
            firefoxDriver.manage().window().maximize();
            wait = new WebDriverWait(firefoxDriver, 10, 250);
            driver = firefoxDriver;
        }
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
    public static WebElement findElement(String xpath) {
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
    public static void click(String xpath) {
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
    public static void sendKeys(String xpath, String textValue) {
        WebElement e = findElement(xpath);
        e.sendKeys(textValue);
        log.debug("Ввел значение '{}' в поле '{}'.", textValue, xpath);
    }

    /**
     * Метод для открытия веб-страницы
     *
     * @param url адресс
     */

    public static void get(String url) {
        driver.get(url);
        log.debug("Открываем страницу '{}'.", url);
    }

    /**
     * Метод для закрытия текущей вкладки
     */

    public static void close() {
       driver.close();
        log.debug("Закрыл вкладку.");
    }

    public static String getUrl() {
        String currentUrl = driver.getCurrentUrl();
        log.debug("Мы находимся на странице с URL '{}'", currentUrl);
        return currentUrl;
    }


    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass(){
        testNGCucumberRunner.finish();
    }
}
