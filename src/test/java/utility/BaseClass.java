package utility;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    public ConfigDataProvider config;
    public ExcelDataProvider excel;

    @BeforeSuite
    public void setUpSuit(){
        config = new ConfigDataProvider();
        excel = new ExcelDataProvider();
    }

    @BeforeClass
    public void setup(){
        driver = BrowserFactory.startApplication(driver,config.getBrowser(),config.getqaURL());
    }

    @AfterClass
    public void tearDown(){
       // BrowserFactory.quitBrowser(driver);
    }
}