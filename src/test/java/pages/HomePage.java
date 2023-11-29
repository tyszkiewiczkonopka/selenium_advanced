package pages;

import components.SearchInputComponent;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;


@Slf4j
public class HomePage extends BasePage {
    private final SearchInputComponent searchInputComponent;

    public HomePage(WebDriver driver) {
        super(driver);
        this.searchInputComponent = new SearchInputComponent(driver);
    }

    @FindBy(css = ".products .product")
    public List<WebElement> products;

    public String enterRandomProductNameIntoSearchField() {
        if (products.isEmpty()) {
            throw new IllegalStateException("The products list is empty. Cannot select a random product.");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(products.size());

        WebElement randomProduct = products.get(randomIndex);
        String productName = getProductName(randomProduct);
        log.info("Random product: "+ productName);
        searchInputComponent.enterProductName(productName);
        return productName;
    }

    public static String getProductName(WebElement product) {
        return product.findElement(By.cssSelector(".product-title a")).getAttribute("innerText");
    }


}
