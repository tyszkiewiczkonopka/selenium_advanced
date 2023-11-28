import configuration.DriverFactory;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected static WebDriver driver;
    @BeforeAll
    public static void setUp() {
        driver = DriverFactory.createDriver();
    }

}
