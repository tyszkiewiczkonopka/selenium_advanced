package pages.common.header;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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

    public SearchInputComponent enterProductName(String productName) {
        searchInput.clear();
        searchInput.sendKeys(productName);
        return new SearchInputComponent(driver);
    }

    public SearchInputComponent clickSearchButton() {
        searchButton.click();
        return new SearchInputComponent(driver);
    }

    public List<String> getSearchResultsDropdown() {
        List<String> dropdownResults = new ArrayList<>();

        defaultWait.until(ExpectedConditions.visibilityOf(searchResultsDropdown));

        for (WebElement result : searchResults) {
            String searchedText = result.getText();
            dropdownResults.add(searchedText);
        }
        return dropdownResults;
    }

}
