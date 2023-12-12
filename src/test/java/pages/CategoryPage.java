package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.components.ProductMiniatureComponent;

import java.util.List;
import java.util.Random;

@Slf4j
@Getter
public class CategoryPage extends BasePage {
    @FindBy(css = ".total-products p")
    private WebElement totalProductsCount;
    @FindBy(css = ".products .product-miniature")
    private List<WebElement> productMiniatures;


    public CategoryPage(WebDriver driver) {
        super(driver);
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
            String productPriceElement =
                    String.valueOf(at(ProductMiniatureComponent.class)
                            .getPrice(at(ProductMiniatureComponent.class).getProductPrice()));

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


}