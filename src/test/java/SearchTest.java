import components.SearchInputComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import providers.UrlProvider;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class SearchTest extends BaseTest {
    HomePage homePage = new HomePage(driver);
    SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
    SearchInputComponent searchInputComponent = new SearchInputComponent(driver);

    //  @Test
    @RepeatedTest(10)
    void randomly_chosen_product_should_be_found_by_its_name_in_search_results() {
        driver.get(UrlProvider.APP);
        String productName = homePage.enterRandomProductNameIntoSearchField();
        searchInputComponent.clickSearchButton();

        assertTrue(searchResultsPage.isProductInSearchResults(productName));
    }

    @Test
    void test_written_into_search_field_should_be_found_among_dropdown_results() {
        driver.get(UrlProvider.APP);
        searchInputComponent.enterProductName("HUMMINGBIRD");
        assertTrue(searchInputComponent.isTextInSearchResultsDropdown(productName));
    }

}
