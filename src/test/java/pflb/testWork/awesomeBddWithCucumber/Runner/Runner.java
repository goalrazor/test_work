package pflb.testWork.awesomeBddWithCucumber.Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"pflb.testWork.awesomeBddWithCucumber.stepdefs"})
public class Runner {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeTest(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
    private static final ThreadLocal<WDriver> driver = new ThreadLocal<>();
    @BeforeTest
    @Parameters("browser")
    public void setBrowser(String browser) {

        driver.set(WDriver.getInstance(browser));
    }


    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterTest(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }
}
