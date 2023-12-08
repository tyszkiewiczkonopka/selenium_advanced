package tests.categories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.CategoryPage;
import pages.components.filters.FiltersSideMenuComponent;
import pages.components.header.HeaderMenuComponent;
import pages.components.ProductMiniatureComponent;
import providers.UrlProvider;
import tests.BaseTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class CategoriesTest extends BaseTest {

    @Test
    void category_names_should_be_displayed_after_entering_through_menu() {
        BaseTest.driver.get(UrlProvider.APP);
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
        BaseTest.driver.get(UrlProvider.APP);
        List<String> menuCategoryIds = at(HeaderMenuComponent.class).getAllCategoryIds();

        for (String categoryId : menuCategoryIds) {
            WebElement category = driver.findElement(By.id(categoryId));
            log.info(">>>>> Starting test for category: " + category.getText());
            at(HeaderMenuComponent.class).hoverOverMenuCategory(category);

            List<String> subcategoryIds = at(HeaderMenuComponent.class).getSubcategoryIds(category);

            for (String subcategoryId : subcategoryIds) {
                category = driver.findElement(By.id(categoryId));
                at(HeaderMenuComponent.class).hoverOverMenuCategory(category);

                WebElement subcategory = driver.findElement(By.id(subcategoryId));
                String expectedSubcategory = subcategory.getText();
                log.info(">>>>> Starting test for subcategory: " + expectedSubcategory);

                subcategory.click();
                String actualSubcategory = at(CategoryPage.class).getOpenedCategoryTitle().toUpperCase();

                assertThat(expectedSubcategory).isEqualTo(actualSubcategory);
                log.info("Expected category name: {}, Actual category name: {}", expectedSubcategory, actualSubcategory);

                assertFilterMenuVisible();
                assertNumberOfProducts();
            }
        }
    }

    private void assertCategoryName(String category) {
        at(HeaderMenuComponent.class).chooseMenuCategory(category);
        String actualCategory = at(CategoryPage.class).getOpenedCategoryTitle().toUpperCase();
        log.info("Expected category name: {}, Actual category name: {}", category, actualCategory);
        assertThat(category).isEqualTo(actualCategory);
    }

    private void assertFilterMenuVisible() {
        assertTrue(at(FiltersSideMenuComponent.class).isFiltersMenuDisplayed(), "Filters menu is not displayed");
    }

    private void assertNumberOfProducts() {
        int totalNumberOfProducts = at(CategoryPage.class).getProductCount();
        int numberOfProductMiniatures = at(CategoryPage.class).getNumberOfProductMiniaturesDisplayed();
        log.info("Total number of products: " + totalNumberOfProducts + ". Number of product miniatures: " + numberOfProductMiniatures);
        assertThat(totalNumberOfProducts).isEqualTo(numberOfProductMiniatures);
    }
}