package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
@Slf4j
public class SearchResultsPage extends BasePage{

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = ".products .product")
    public List<WebElement> products;

    public boolean isProductInSearchResults(String productName) {
        for (WebElement searchResult : products) {
            String resultText = at(HomePage.class).getProductName(searchResult);
            if (resultText.contains(productName)) {
                log.info("Product '{}' found in search results.", productName);
                return true;
            }
        }
        log.info("Product '{}' not found in search results.", productName);
        return false;
    }
}
