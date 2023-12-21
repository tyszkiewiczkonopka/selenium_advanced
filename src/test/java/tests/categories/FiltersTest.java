package tests.categories;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import pages.categories.category.CategoryPage;
import pages.categories.filters.FilterPriceRangeComponent;
import pages.categories.filters.FiltersSideMenuComponent;
import providers.url.UrlProvider;
import tests.base.BaseTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class FiltersTest extends BaseTest {

    @Test
    public void price_filter_should_show_products_within_price_range() {
        driver.get(UrlProvider.CATEGORY_ACCESSORIES);
        int initialNumberOfProducts = at(CategoryPage.class).getNumberOfProductMiniaturesDisplayed();

        BigDecimal minTargetPrice = new BigDecimal("13.00");
        BigDecimal maxTargetPrice = new BigDecimal("19.00");

        assertTargetPriceRangeIsSet(minTargetPrice, maxTargetPrice);
        assertFilteredProductWithinPriceRange(minTargetPrice, maxTargetPrice);
        assertClearedFiltersShowInitialNumberOfProducts(initialNumberOfProducts);
    }


    private void assertTargetPriceRangeIsSet(BigDecimal minTargetPrice, BigDecimal maxTargetPrice) {
        log.info(">>>>> Set a new price range");
        FilterPriceRangeComponent priceFilter = at(FilterPriceRangeComponent.class);

        priceFilter
                .moveMin(minTargetPrice)
                .allSpinnersOff(FilterPriceRangeComponent.class);
        priceFilter
                .moveMax(maxTargetPrice)
                .allSpinnersOff(FilterPriceRangeComponent.class);

        BigDecimal minActualPrice = priceFilter.getActualMinPriceValue();
        BigDecimal maxActualPrice = priceFilter.getActualMaxPriceValue();

        assertThat(minActualPrice).isEqualTo(minTargetPrice);
        assertThat(maxActualPrice).isEqualTo(maxTargetPrice);
        log.info("Actual price range: " + minActualPrice + " - " + maxActualPrice);
        log.info("Target price range: " + minTargetPrice + " - " + maxTargetPrice);
    }


    private void assertFilteredProductWithinPriceRange(BigDecimal minTargetPrice, BigDecimal maxTargetPrice) {
        log.info(">>>>> Check if only products from selected price range are visible");

        List<BigDecimal> productPrices = at(CategoryPage.class).getProductPricesFromAllMiniatures();

        SoftAssertions softAssertions = new SoftAssertions();

        for (BigDecimal productPrice : productPrices) {
            softAssertions.assertThat(productPrice).isBetween(minTargetPrice, maxTargetPrice);
        }

        log.info("Products with prices {} are within price range {} - {}",
                productPrices, minTargetPrice, maxTargetPrice);

        softAssertions.assertAll();

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
