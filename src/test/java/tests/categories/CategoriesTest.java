package tests.categories;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.categories.category.CategoryPage;
import pages.categories.filters.FiltersSideMenuComponent;
import pages.common.header.HeaderMenuComponent;
import providers.url.UrlProvider;
import tests.base.BaseTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class CategoriesTest extends BaseTest {

    @Test
    void category_names_should_be_displayed_after_entering_through_menu() {
        driver.get(UrlProvider.APP);
        List<String> menuCategories = at(HeaderMenuComponent.class).getAllCategoryNames();

        for (String category : menuCategories) {
            log.info(">>>>> Starting test for category: " + category);
            assertCategoryName(category);
            assertFilterMenuVisible();
            assertNumberOfProducts();
        }
    }

    @Test
    void subcategory_names_should_be_displayed_after_entering_through_menu() {
        driver.get(UrlProvider.APP);
        List<String> menuCategoryIds = at(HeaderMenuComponent.class).getAllCategoryIds();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(menuCategoryIds).isNotEmpty();

        for (String categoryId : menuCategoryIds) {
            WebElement category = driver.findElement(By.id(categoryId));
            log.info(">>>>> Starting test for category: " + category.getText());
            at(HeaderMenuComponent.class).hoverOverMenuCategory(category);

            List<String> subcategoryIds = at(HeaderMenuComponent.class).getSubcategoryIds(category);

            if (subcategoryIds.isEmpty()) {
                log.info("No subcategories found for category: " + category.getText());
                continue;
            }

            for (String subcategoryId : subcategoryIds) {
                category = driver.findElement(By.id(categoryId));
                at(HeaderMenuComponent.class).hoverOverMenuCategory(category);

                WebElement subcategory = driver.findElement(By.id(subcategoryId));
                String expectedSubcategory = subcategory.getText();
                log.info(">>>>> Starting test for subcategory: " + expectedSubcategory);

                subcategory.click();
                String actualSubcategory = at(CategoryPage.class).getOpenedCategoryTitle().toUpperCase();

                softAssertions.assertThat(expectedSubcategory).isEqualTo(actualSubcategory);
                log.info("Expected category name: {}, Actual category name: {}", expectedSubcategory, actualSubcategory);

                assertFilterMenuVisible();
                assertNumberOfProducts();
            }
        }

        softAssertions.assertAll();
    }


    private void assertCategoryName(String category) {
        at(HeaderMenuComponent.class).chooseMenuCategory(category);
        String actualCategory = at(CategoryPage.class).getOpenedCategoryTitle().toUpperCase();

        assertThat(category).isEqualTo(actualCategory);

        log.info("Expected category name: {}, Actual category name: {}", category, actualCategory);
    }

    private void assertFilterMenuVisible() {
        assertThat(at(FiltersSideMenuComponent.class).getFiltersMenu().isDisplayed()).isTrue();
    }

    private void assertNumberOfProducts() {
        int totalNumberOfProducts = at(CategoryPage.class).getProductCount();
        int numberOfProductMiniatures = at(CategoryPage.class).getNumberOfProductMiniaturesDisplayed();

        log.info("Total number of products: " + totalNumberOfProducts + ". Number of product miniatures: " + numberOfProductMiniatures);

        assertThat(totalNumberOfProducts).isEqualTo(numberOfProductMiniatures);
    }
}