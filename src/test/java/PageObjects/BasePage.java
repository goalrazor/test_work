package PageObjects;

import Runner.Runner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class BasePage {
    static final Logger log = LogManager.getLogger();

    public void open(String url) {
        Runner.get(url);
    }

    public void close() {
       Runner.close();
    }
}
