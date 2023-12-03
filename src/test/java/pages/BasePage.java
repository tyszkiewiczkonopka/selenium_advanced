package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {
    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait defaultWait;
    public BasePage(WebDriver driver){
        init(driver);
        PageFactory.initElements(driver,this);
    }
    private void init(WebDriver driver){
        this.driver = driver;
        this.actions = new Actions(driver);
        this.defaultWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(css = ".spinner")
    private WebElement spinner;

    public void allSpinnersOff(){
        defaultWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".spinner"), 0));
    }
}
