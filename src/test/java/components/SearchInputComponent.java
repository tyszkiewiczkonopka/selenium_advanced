package components;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;
@Slf4j
public class SearchInputComponent extends BasePage {
    public SearchInputComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[aria-label='Search']")
    private WebElement searchInput;
    @FindBy(id = "ui-id-1")
    private WebElement searchResultsDropdown;
    @FindBy(css = "#ui-id-1 li")
    List<WebElement> searchResults;

    @FindBy(css = "button[type=\"submit\"] .material-icons.search")
    private WebElement searchButton;

    public void enterProductName(String productName) {
        searchInput.clear();
        searchInput.sendKeys(productName);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public boolean isTextInSearchResultsDropdown(String productName) {
        for(WebElement result : searchResults){
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
