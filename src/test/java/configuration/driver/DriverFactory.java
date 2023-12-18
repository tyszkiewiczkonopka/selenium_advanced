package configuration.driver;

import configuration.browser.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static configuration.driver.DriverYamlReader.getActiveBrowserFromConfigFile;

public class DriverFactory {

    public static WebDriver createDriver() {
        Browser activeBrowser = getActiveBrowserFromConfigFile();
        return getDriverFromActiveBrowser(activeBrowser);
    }

    private static WebDriver getDriverFromActiveBrowser(Browser activeBrowser) {
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
