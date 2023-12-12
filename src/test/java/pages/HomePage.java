package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.components.header.SearchInputComponent;

import java.util.List;
import java.util.Random;

@Getter
@Slf4j
public class HomePage extends BasePage {

    @FindBy(css = ".products .product")
    public List<WebElement> products;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String writeRandomProductNameIntoSearchField() {
        if (products.isEmpty()) {
            throw new IllegalStateException("The products list is empty. Cannot select a random product.");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(products.size());

        WebElement randomProduct = products.get(randomIndex);
        String productName = getProductName(randomProduct);
        log.info("Random product: " + productName);

        at(SearchInputComponent.class).enterProductName(productName);
        return productName;
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
