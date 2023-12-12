package pages.components.header;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import pages.SearchResultsPage;

import java.util.List;

@Slf4j
@Getter
public class SearchInputComponent extends BasePage {

    @FindBy(css = "#ui-id-1 li")
    List<WebElement> searchResults;
    @FindBy(css = "input[aria-label='Search']")
    private WebElement searchInput;
    @FindBy(id = "ui-id-1")
    private WebElement searchResultsDropdown;
    @FindBy(css = "button[type=\"submit\"] .material-icons.search")
    private WebElement searchButton;

    public SearchInputComponent(WebDriver driver) {
        super(driver);
    }

    public void enterProductName(String productName) {
        searchInput.clear();
        searchInput.sendKeys(productName);
    }

    public SearchResultsPage clickSearchButton() {
        searchButton.click();
        return new SearchResultsPage(driver);
    }

    public boolean isTextInSearchResultsDropdown(String productName) {
        defaultWait.until(ExpectedConditions.visibilityOf(searchResultsDropdown));
        for (WebElement result : searchResults) {
            String searchedText = result.getText();
            if (searchedText.contains(productName)) {
                log.info("Product '{}' found in search results.", productName);
                return true;
            }
        }
        log.info("Product '{}' not found in search results.", productName);
        return false;
    }

}
