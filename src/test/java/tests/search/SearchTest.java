package tests.search;

import pages.components.SearchInputComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import pages.HomePage;
import pages.SearchResultsPage;
import providers.UrlProvider;
import tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class SearchTest extends BaseTest {
    HomePage homePage = new HomePage(BaseTest.driver);
    SearchResultsPage searchResultsPage = new SearchResultsPage(BaseTest.driver);
    SearchInputComponent searchInputComponent = new SearchInputComponent(BaseTest.driver);

    //  @Test
    @RepeatedTest(10)
    void randomly_chosen_product_should_be_found_by_its_name_in_search_results() {
        BaseTest.driver.get(UrlProvider.APP);
        String productName = homePage.enterRandomProductNameIntoSearchField();
        searchInputComponent.clickSearchButton();

        assertTrue(searchResultsPage.isProductInSearchResults(productName));
    }

    @RepeatedTest(10)
    void test_written_into_search_field_should_be_found_among_dropdown_results() {
        BaseTest.driver.get(UrlProvider.APP);
        String productName = "HUMMINGBIRD";
        searchInputComponent.enterProductName(productName);

        assertTrue(searchInputComponent.isTextInSearchResultsDropdown(productName));
    }

}