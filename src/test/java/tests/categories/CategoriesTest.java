package tests.categories;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import pages.categories.category.CategoryPage;
import pages.categories.filters.FiltersSideMenuComponent;
import pages.common.header.HeaderMenuComponent;
import providers.url.UrlProvider;
import tests.base.BaseTest;

import java.util.List;

@Slf4j
public class CategoriesTest extends BaseTest {
    SoftAssertions softAssertions = new SoftAssertions();


    @Test
    void category_names_should_be_displayed_after_entering_through_menu() {
        driver.get(UrlProvider.APP);
        List<String> menuCategories = at(HeaderMenuComponent.class).getAllCategoryNames();

        for (String category : menuCategories) {
            log.info(">>>>> Starting test for category: " + category);
            assertCategoryName(category, softAssertions);
            assertFilterMenuVisible(softAssertions);
            assertNumberOfProducts(softAssertions);
        }
    }

    @Test
    void subcategory_names_should_be_displayed_after_entering_through_menu() {
        driver.get(UrlProvider.APP);
        List<String> menuCategoryIds = at(HeaderMenuComponent.class).getAllCategoryIds();

        softAssertions.assertThat(menuCategoryIds).isNotEmpty();

        for (String categoryId : menuCategoryIds) {
            List<String> subcategoryIds = at(HeaderMenuComponent.class)
                    .getSubcategoryIds(categoryId);

            if (subcategoryIds.isEmpty()) {
                log.info("No subcategories found for category: " + categoryId);
                continue;
            }

            for (String subcategoryId : subcategoryIds) {
                var expectedSubcategory = at(HeaderMenuComponent.class)
                        .getSubcategoryNameAndOpen(categoryId, subcategoryId);
                log.info(">>>>> Starting test for subcategory: " + expectedSubcategory);

                String actualSubcategory = at(CategoryPage.class).getOpenedCategoryTitle().toUpperCase();

                softAssertions.assertThat(expectedSubcategory).isEqualTo(actualSubcategory);
                log.info("Expected category name: {}, Actual category name: {}",
                        expectedSubcategory, actualSubcategory);

                assertFilterMenuVisible(softAssertions);
                assertNumberOfProducts(softAssertions);
            }
        }

        softAssertions.assertAll();
    }

    private void assertCategoryName(String category, SoftAssertions softAssertions) {
        at(HeaderMenuComponent.class).chooseMenuCategory(category);
        String actualCategory = at(CategoryPage.class).getOpenedCategoryTitle().toUpperCase();

        softAssertions.assertThat(category).isEqualTo(actualCategory);

        log.info("Expected category name: {}, Actual category name: {}", category, actualCategory);
    }

    private void assertFilterMenuVisible(SoftAssertions softAssertions) {
        softAssertions.assertThat(at(FiltersSideMenuComponent.class)
                .getFiltersMenu().isDisplayed()).isTrue();
    }

    private void assertNumberOfProducts(SoftAssertions softAssertions) {
        int totalNumberOfProducts = at(CategoryPage.class).getProductCount();
        int numberOfProductMiniatures = at(CategoryPage.class).getNumberOfProductMiniaturesDisplayed();

        log.info("Total number of products: " + totalNumberOfProducts + ". Number of product miniatures: " + numberOfProductMiniatures);

        softAssertions.assertThat(totalNumberOfProducts).isEqualTo(numberOfProductMiniatures);
    }

}