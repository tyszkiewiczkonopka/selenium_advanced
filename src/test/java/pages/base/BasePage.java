package pages.base;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;


public class BasePage {
    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait defaultWait;

    public BasePage(WebDriver driver) {
        init(driver);
        PageFactory.initElements(driver, this);
    }
    public BasePage(WebDriver driver, WebElement parent) {
        init(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(parent), this);
    }

    private void init(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.defaultWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public BigDecimal getPrice(WebElement priceElement) {
        String productPriceText = priceElement.getText();
        String barePriceText = productPriceText.replaceAll("[^\\d.]", "");
        return new BigDecimal(barePriceText);
    }

    public String getText(WebElement textElement) {
        return textElement.getText();
    }

    public void click(WebElement clickElement) {
        clickElement.click();
    }
    public void selectVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    public int getInputValueAsInt(WebElement inputElement){
        return Integer.parseInt(inputElement.getAttribute("value"));
    }

    @SneakyThrows
    public <T extends BasePage> T at(Class<T> pageType) {
        return pageType.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }

    public <T extends BasePage> T allSpinnersOff(Class<T> pageType) {
        defaultWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".spinner"), 0));
        return pageType.cast(this);
    }
}
