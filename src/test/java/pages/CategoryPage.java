package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.components.HeaderMenuComponent;
import pages.components.ProductMiniatureComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryPage extends BasePage {
//    HeaderMenuComponent headerMenuComponent;
//    ProductMiniatureComponent productMiniatureComponent;

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public String getOpenedCategoryTitle() {
        return driver.getTitle();
    }

    @FindBy(id = "search_filters")
    private WebElement filtersMenu;
    @FindBy(css = ".total-products p")
    private WebElement totalProductsCount;
    @FindBy(css = ".products .product-miniature")
    private List<WebElement> productMiniatures;


    public boolean isFiltersMenuDisplayed() {
        return filtersMenu.isDisplayed();
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
}
