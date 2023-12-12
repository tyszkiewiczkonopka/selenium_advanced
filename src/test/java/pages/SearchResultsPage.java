package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Slf4j
@Getter
public class SearchResultsPage extends BasePage {

    @FindBy(css = ".products .product")
    public List<WebElement> products;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

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
