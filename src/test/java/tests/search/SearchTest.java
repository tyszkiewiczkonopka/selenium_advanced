package tests.search;

import org.junit.jupiter.api.Test;
import pages.components.header.SearchInputComponent;
import lombok.extern.slf4j.Slf4j;
import pages.HomePage;
import pages.SearchResultsPage;
import providers.UrlProvider;
import tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class SearchTest extends BaseTest {
    @Test
    void randomly_chosen_product_should_be_found_by_its_name_in_search_results() {
        BaseTest.driver.get(UrlProvider.APP);
        String productName = at(HomePage.class).writeRandomProductNameIntoSearchField();
        at(SearchInputComponent.class).clickSearchButton();

        assertTrue(at(SearchResultsPage.class).isProductInSearchResults(productName));
    }

    @Test
    void test_written_into_search_field_should_be_found_among_dropdown_results() {
        BaseTest.driver.get(UrlProvider.APP);
        String productName = "HUMMINGBIRD";
        at(SearchInputComponent.class).enterProductName(productName);

        assertTrue(at(SearchInputComponent.class).isTextInSearchResultsDropdown(productName));
    }
}
