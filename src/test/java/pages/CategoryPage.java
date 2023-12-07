package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.components.ProductMiniatureComponent;

import java.util.List;
import java.util.Random;

@Slf4j
public class CategoryPage extends BasePage {

    ProductMiniatureComponent productMiniatureComponent;
    ProductPage productPage;
    @FindBy(css = ".total-products p")
    private WebElement totalProductsCount;
    @FindBy(css = ".products .product-miniature")
    private List<WebElement> productMiniatures;


    public CategoryPage(WebDriver driver) {
        super(driver);
        productMiniatureComponent = new ProductMiniatureComponent(driver);
        productPage = new ProductPage(driver);
    }

    public String getOpenedCategoryTitle() {
        return driver.getTitle();
    }

    public int getProductCount() {
        defaultWait.until(ExpectedConditions.visibilityOf(totalProductsCount));
        String countText = totalProductsCount.getText().trim();
        return Integer.parseInt(countText.split(" ")[2]);
    }

    public int getNumberOfProductMiniaturesDisplayed() {
        defaultWait.until(ExpectedConditions.visibilityOfAllElements(productMiniatures));
        return productMiniatures.size();
    }

    public void areProductsWithinFilteredPriceRange(Double minTargetPrice, Double maxTargetPrice) {
        for (WebElement product : productMiniatures) {
            String productPriceElement = String.valueOf(productMiniatureComponent.getProductPrice());
            String productPriceText = productPriceElement.replace("$", "");
            try {
                Double productPrice = Double.valueOf(productPriceText);

                if (productPrice >= minTargetPrice && productPrice <= maxTargetPrice) {
                    log.info("Product with price {} is in the specified range '{} - {}'.", productPrice, minTargetPrice, maxTargetPrice);
                    return;
                }
            } catch (NumberFormatException e) {
                log.warn("Invalid product price format: {}", productPriceText);
            }

        }
    }

    public void addRandomProductToCart() {
        WebElement randomProduct = selectRandomProduct();
        String randomProductName = productMiniatureComponent.getProductNameFromMiniature(randomProduct);
        productMiniatureComponent.openProductView(randomProductName);
        productPage.clickAddToCart();
        driver.navigate().back();
        log.info("Added product '{}' to the cart.", randomProductName);
    }

    public WebElement selectRandomProduct() {
        if (productMiniatureComponent.getAllMiniatures().isEmpty()) {
            throw new IllegalStateException("No products found on the page.");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(productMiniatureComponent.getAllMiniatures().size());
        return productMiniatureComponent.getAllMiniatures().get(randomIndex);
    }

}