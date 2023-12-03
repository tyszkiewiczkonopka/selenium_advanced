package tests.categories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pages.CategoryPage;
import pages.components.FilterPriceRangeComponent;
import pages.components.FiltersSideMenuComponent;
import providers.UrlProvider;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class FiltersTest extends BaseTest {
    FilterPriceRangeComponent filterPriceRangeComponent;
    CategoryPage categoryPage;
    FiltersSideMenuComponent filtersSideMenuComponent;

    @BeforeEach
    public void setUpLoginTest() {
        filterPriceRangeComponent = new FilterPriceRangeComponent(driver);
        categoryPage = new CategoryPage(driver);
        filtersSideMenuComponent = new FiltersSideMenuComponent(driver);
    }

    @Test
    public void price_filter_should_show_products_within_price_range() {
        BaseTest.driver.get(UrlProvider.CATEGORY_ACCESSORIES);
        int initialNumberOfProducts = categoryPage.getNumberOfProductMiniaturesDisplayed();

        Double minTargetPrice = 13.00;
        Double maxTargetPrice = 15.00;

        assertTargetPriceRangeIsSet(minTargetPrice, maxTargetPrice);
        assertFilteredProductWithinPriceRange(minTargetPrice, maxTargetPrice);
        assertClearedFiltersShowInitialNumberOfProducts(initialNumberOfProducts);
    }

    private void assertTargetPriceRangeIsSet(Double minTargetPrice, Double maxTargetPrice) {
        log.info(">>>>> Set a new price range");
        filterPriceRangeComponent.moveMin(minTargetPrice);
        filterPriceRangeComponent.allSpinnersOff();
        filterPriceRangeComponent.moveMax(maxTargetPrice);
        filterPriceRangeComponent.allSpinnersOff();

        Double minActualPrice = filterPriceRangeComponent.getActualMinPriceValue();
        Double maxActualPrice = filterPriceRangeComponent.getActualMaxPriceValue();

        assertThat(minActualPrice).isEqualTo(minTargetPrice);
        assertThat(maxActualPrice).isEqualTo(maxTargetPrice);
        log.info("Actual price range: " + minActualPrice + " - " + maxActualPrice);
        log.info("Target price range: " + minTargetPrice + " - " + maxTargetPrice);
    }

    private void assertFilteredProductWithinPriceRange(Double minTargetPrice, Double maxTargetPrice) {
        log.info(">>>>> Check if only products from selected price range are visible");
        categoryPage.areProductsWithinFilteredPriceRange(minTargetPrice, maxTargetPrice);
    }

    private void assertClearedFiltersShowInitialNumberOfProducts(int initialNumberOfProducts) {
        log.info(">>>>> Check if clearing filters results in showing initial number of products");
        filtersSideMenuComponent.clearAllFilters();
        filtersSideMenuComponent.allSpinnersOff();
        int currentNumberOfProducts = categoryPage.getNumberOfProductMiniaturesDisplayed();

        assertThat(currentNumberOfProducts).isEqualTo(initialNumberOfProducts);
        log.info("Initial number of products: " + initialNumberOfProducts);
        log.info("Current number of products: " + currentNumberOfProducts);
    }

}