package PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stepdefs.WDriver;

public abstract class BasePage {
    static final Logger log = LogManager.getLogger();
     WDriver driver = WDriver.getInstance();

    public void open(String url){
        driver.get(url);
    }

    public void close(){
        driver.close();
    }
}
