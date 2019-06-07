package stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ServiceHooks extends WDriver{
    private WDriver driver;
    @Before
    public void initializeTest(){
         driver = WDriver.getInstance();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        driver.close();
    }
}
