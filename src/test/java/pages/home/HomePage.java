package pages.home;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;
import pages.common.header.SearchInputComponent;

import java.util.List;
import java.util.Random;

@Slf4j
public class HomePage extends BasePage {

    @FindBy(css = ".products .product")
    public List<WebElement> products;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void enterRandomProductNameIntoSearchField(String randomProductName) {
        at(SearchInputComponent.class).enterProductName(randomProductName);
    }

    public String getProductName(WebElement product) {
        return product.findElement(By.cssSelector(".product-title a")).getAttribute("innerText");
    }

    public String getRandomProductName() {
        Random random = new Random();
        defaultWait.until(ExpectedConditions.visibilityOfAllElements(products));
        int randomIndex = random.nextInt(products.size());
        WebElement randomProduct = products.get(randomIndex);

        return getProductName(randomProduct);
    }


}
