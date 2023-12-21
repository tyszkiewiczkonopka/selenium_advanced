package pages.categories.category;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CategoryPage extends BasePage {
    @FindBy(css = ".total-products p")
    private WebElement totalProductsCount;
    @FindBy(css = ".products .product-miniature")
    private List<WebElement> productMiniatures;
    @FindBy(css = "span.price")
    private List<WebElement> allProductPrices;


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

    public List<BigDecimal> getProductPricesFromAllMiniatures() {
        defaultWait.until(ExpectedConditions.visibilityOfAllElements(productMiniatures));
        return allProductPrices.stream()
                .map(this::getPrice)
                .collect(Collectors.toList());
    }

}