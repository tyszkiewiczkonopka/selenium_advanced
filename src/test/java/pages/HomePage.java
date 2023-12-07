package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.components.ProductMiniatureComponent;
import pages.components.cart.AddToCartPopupComponent;
import pages.components.header.SearchInputComponent;

import java.util.List;
import java.util.Random;


@Slf4j
public class HomePage extends BasePage {
    SearchInputComponent searchInputComponent;
    ProductMiniatureComponent productMiniatureComponent;
    ProductPage productPage;
    AddToCartPopupComponent addToCartPopupComponent;

    public HomePage(WebDriver driver) {
        super(driver);
        searchInputComponent = new SearchInputComponent(driver);
        productMiniatureComponent = new ProductMiniatureComponent(driver);
        productPage = new ProductPage(driver);
        addToCartPopupComponent = new AddToCartPopupComponent(driver);
    }

    @FindBy(css = ".products .product")
    public List<WebElement> products;

    public String writeRandomProductNameIntoSearchField() {
        if (products.isEmpty()) {
            throw new IllegalStateException("The products list is empty. Cannot select a random product.");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(products.size());

        WebElement randomProduct = products.get(randomIndex);
        String productName = getProductName(randomProduct);
        log.info("Random product: " + productName);
        searchInputComponent.enterProductName(productName);
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

    public void addToCartRandomProduct() {
        String homePageRandomProductName = getRandomProductName();
        productMiniatureComponent.openProductView(homePageRandomProductName);
        String randomQuantity = String.valueOf(productPage.getRandomQuantity());
        productPage.setQuantity(randomQuantity);
        productPage.clickAddToCart();
        log.info("Added to cart: " + homePageRandomProductName + " x " + randomQuantity);
    }

    public void addToCartMultipleRandomProducts(int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            addToCartRandomProduct();
            if (i == numberOfProducts - 1) {
             addToCartPopupComponent.proceedToCheckout();
            } else {
                driver.navigate().back();
            }
        }
    }

}
