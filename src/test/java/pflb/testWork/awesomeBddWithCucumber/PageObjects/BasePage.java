package pflb.testWork.awesomeBddWithCucumber.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pflb.testWork.awesomeBddWithCucumber.Runner.WDriver;


public abstract class BasePage {
    static final Logger log = LogManager.getLogger();

    public void open(String url) {
        WDriver.get(url);
    }

    public void close() {
       WDriver.close();
    }
}
