package configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static configuration.BrowserType.CHROME;

public class DriverFactory {

    private static final String CONFIGURATION_FILE_PATH = "src/test/resources/configuration.yml";

    public static Browser getActiveBrowser() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Config config = mapper.readValue(new File(CONFIGURATION_FILE_PATH), Config.class);
            Optional<Browser> activeBrowser = config.getBrowsers().entrySet().stream()
                    .map((singleBrowser) -> {
                        return singleBrowser.getValue().setBrowserType(singleBrowser.getKey());
                    })
                    .filter(Browser::isActive)
                    .findFirst();

            return activeBrowser.orElseThrow(() -> new IllegalArgumentException("No active browser configuration found"));

        } catch (IOException e) {
            throw new RuntimeException("Error reading YAML file: " + e.getMessage());
        }
    }

    public static WebDriver createDriver() {
        Browser activeBrowser = getActiveBrowser();
        return createDriver(activeBrowser);
    }

    private static WebDriver createDriver(Browser activeBrowser) {
        if (activeBrowser == null) {
            throw new IllegalArgumentException("Browser name cannot be null.");
        }

        return switch (activeBrowser.getBrowserType()) {
            case CHROME -> createChromeDriver();
            case FIREFOX -> createFirefoxDriver();
            case IE -> createInternetExplorerDriver();
            case EDGE -> createEdgeDriver();
            default -> throw new IllegalArgumentException("Invalid browser specified: " + activeBrowser);
        };
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        return new FirefoxDriver(options);
    }

    private static WebDriver createInternetExplorerDriver() {
        return new InternetExplorerDriver();
    }

    private static WebDriver createEdgeDriver() {
        return new EdgeDriver();
    }
}
