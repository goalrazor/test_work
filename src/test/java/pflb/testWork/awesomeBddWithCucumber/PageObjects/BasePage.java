package pflb.testWork.awesomeBddWithCucumber.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pflb.testWork.awesomeBddWithCucumber.Runner.Runner;


public abstract class BasePage {
    static final Logger log = LogManager.getLogger();

    public void open(String url) {
        Runner.get(url);
    }

    public void close() {
       Runner.close();
    }
}
