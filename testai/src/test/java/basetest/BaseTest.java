package basetest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public  void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @BeforeClass(alwaysRun = true)
    public  void openHomePage(){
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
//        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://bees.akademijait.vtmc.lt/darzelis");
    }

    @AfterClass(alwaysRun = true)
    public  void closeBrowser() {
        driver.manage().deleteAllCookies();
       //for Firefox browser, lines close and quit should be commented
        driver.close();
        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    protected  void tearDown() {
        driver.quit();
    }
}