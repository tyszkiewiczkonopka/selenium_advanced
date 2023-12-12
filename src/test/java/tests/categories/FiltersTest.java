package tests.categories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import pages.CategoryPage;
import pages.components.filters.FilterPriceRangeComponent;
import pages.components.filters.FiltersSideMenuComponent;
import providers.UrlProvider;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class FiltersTest extends BaseTest {
    FilterPriceRangeComponent priceFilter = at(FilterPriceRangeComponent.class);

    @Test
    public void price_filter_should_show_products_within_price_range() {
        BaseTest.driver.get(UrlProvider.CATEGORY_ACCESSORIES);
        int initialNumberOfProducts = at(CategoryPage.class).getNumberOfProductMiniaturesDisplayed();

        Double minTargetPrice = 13.00;
        Double maxTargetPrice = 19.00;

        assertTargetPriceRangeIsSet(minTargetPrice, maxTargetPrice);
        assertFilteredProductWithinPriceRange(minTargetPrice, maxTargetPrice);
        assertClearedFiltersShowInitialNumberOfProducts(initialNumberOfProducts);
    }


    private void assertTargetPriceRangeIsSet(Double minTargetPrice, Double maxTargetPrice) {
        log.info(">>>>> Set a new price range");
        priceFilter
                .moveMin(minTargetPrice)
                .allSpinnersOff(FilterPriceRangeComponent.class);
        priceFilter
                .moveMax(maxTargetPrice)
                .allSpinnersOff(FilterPriceRangeComponent.class);

        Double minActualPrice = priceFilter.getActualMinPriceValue();
        Double maxActualPrice = priceFilter.getActualMaxPriceValue();

        assertThat(minActualPrice).isEqualTo(minTargetPrice);
        assertThat(maxActualPrice).isEqualTo(maxTargetPrice);
        log.info("Actual price range: " + minActualPrice + " - " + maxActualPrice);
        log.info("Target price range: " + minTargetPrice + " - " + maxTargetPrice);
    }


    private void assertFilteredProductWithinPriceRange(Double minTargetPrice, Double maxTargetPrice) {
        log.info(">>>>> Check if only products from selected price range are visible");
        at(CategoryPage.class).areProductsWithinFilteredPriceRange(minTargetPrice, maxTargetPrice);
    }

    private void assertClearedFiltersShowInitialNumberOfProducts(int initialNumberOfProducts) {
        log.info(">>>>> Check if clearing filters results in showing initial number of products");
        at(FiltersSideMenuComponent.class)
                .clearAllFilters()
                .allSpinnersOff(FiltersSideMenuComponent.class);
        int currentNumberOfProducts = at(CategoryPage.class).getNumberOfProductMiniaturesDisplayed();

        assertThat(currentNumberOfProducts).isEqualTo(initialNumberOfProducts);
        log.info("Initial number of products: " + initialNumberOfProducts);
        log.info("Current number of products: " + currentNumberOfProducts);
    }


}
