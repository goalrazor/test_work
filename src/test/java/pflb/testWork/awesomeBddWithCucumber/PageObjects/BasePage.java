package pflb.testWork.awesomeBddWithCucumber.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pflb.testWork.awesomeBddWithCucumber.Runner.WDriver;


public abstract class BasePage {
    final Logger log = LogManager.getLogger();
    WDriver driver = WDriver.getInstance();


    void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.close();
    }
}
