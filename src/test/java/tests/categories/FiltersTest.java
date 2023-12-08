package tests.categories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import pages.CategoryPage;
import pages.components.filters.FilterPriceRangeComponent;
import pages.components.filters.FiltersSideMenuComponent;
import providers.UrlProvider;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class FiltersTest extends BaseTest {
    @Test
    public void price_filter_should_show_products_within_price_range() {
        BaseTest.driver.get(UrlProvider.CATEGORY_ACCESSORIES);
        int initialNumberOfProducts = at(CategoryPage.class).getNumberOfProductMiniaturesDisplayed();

        Double minTargetPrice = 13.00;
        Double maxTargetPrice = 15.00;

        assertTargetPriceRangeIsSet(minTargetPrice, maxTargetPrice);
        assertFilteredProductWithinPriceRange(minTargetPrice, maxTargetPrice);
        assertClearedFiltersShowInitialNumberOfProducts(initialNumberOfProducts);
    }


    private void assertTargetPriceRangeIsSet(Double minTargetPrice, Double maxTargetPrice) {
        log.info(">>>>> Set a new price range");
        at(FilterPriceRangeComponent.class)
                .moveMin(minTargetPrice)
                .allSpinnersOff(FilterPriceRangeComponent.class);
        at(FilterPriceRangeComponent.class)
                .moveMax(maxTargetPrice)
                .allSpinnersOff(FilterPriceRangeComponent.class);

        Double minActualPrice =  at(FilterPriceRangeComponent.class).getActualMinPriceValue();
        Double maxActualPrice =  at(FilterPriceRangeComponent.class).getActualMaxPriceValue();

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
