package tests.search;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.search.SearchResultsPage;
import pages.common.header.SearchInputComponent;
import providers.url.UrlProvider;
import tests.base.BaseTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SearchTest extends BaseTest {
    @Test
    void randomly_chosen_product_should_be_found_by_its_name_in_search_results() {
        driver.get(UrlProvider.APP);

        String productName = at(HomePage.class).getRandomProductName();
        at(SearchInputComponent.class)
                .enterProductName(productName)
                .clickSearchButton();

        assertThat(at(SearchResultsPage.class).getSearchResults()).contains(productName);
    }

    @Test
    void test_written_into_search_field_should_be_found_among_dropdown_results() {
        driver.get(UrlProvider.APP);
        String productName = "HUMMINGBIRD";

        at(SearchInputComponent.class).enterProductName(productName);
        List<String> dropdownResults = at(SearchInputComponent.class).getSearchResultsDropdown();

        assertThat(dropdownResults).anyMatch(result -> result.contains(productName));
    }
}
