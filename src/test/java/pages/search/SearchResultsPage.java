package pages.search;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.home.HomePage;

import java.util.List;

@Slf4j
public class SearchResultsPage extends BasePage {

    @FindBy(css = ".products .product")
    public List<WebElement> products;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getSearchResults() {
        HomePage homePage = at(HomePage.class);
        return products.stream().map(homePage::getProductName).toList();
    }

}
