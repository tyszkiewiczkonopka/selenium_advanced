package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {
    WebDriver driver;
    Actions actions;
    WebDriverWait defaultWait;
    public BasePage(WebDriver driver){
        init(driver);
        PageFactory.initElements(driver,this);
    }
    private void init(WebDriver driver){
        this.driver = driver;
        this.actions = new Actions(driver);
        this.defaultWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
