package helpers;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected WebDriver driver;
    protected Duration pageloadtimout  = Duration.ofSeconds(30);
    protected Duration impliciwait  = Duration.ofSeconds(20);
    
    @BeforeMethod
    public void setup() {
    	ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(pageloadtimout);
		driver.manage().timeouts().implicitlyWait(impliciwait);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
