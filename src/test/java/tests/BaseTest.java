package tests;

import configuration.DriverFactory;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected WebDriverWait defaultWait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @BeforeAll
    public static void setUp() {
        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @SneakyThrows
    public <T extends BasePage> T at(Class<T> pageType) {
        return pageType.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }
}
