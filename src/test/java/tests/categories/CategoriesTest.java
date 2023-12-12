package tests.categories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.CategoryPage;
import pages.components.filters.FiltersSideMenuComponent;
import pages.components.header.HeaderMenuComponent;
import providers.UrlProvider;
import tests.BaseTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class CategoriesTest extends BaseTest {
    HeaderMenuComponent headerMenu = at(HeaderMenuComponent.class);
    CategoryPage categoryPage = at(CategoryPage.class);

    @Test
    void category_names_should_be_displayed_after_entering_through_menu() {
        BaseTest.driver.get(UrlProvider.APP);
        List<String> menuCategories = headerMenu.getAllCategoryNames();

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
        List<String> menuCategoryIds = headerMenu.getAllCategoryIds();

        for (String categoryId : menuCategoryIds) {
            WebElement category = driver.findElement(By.id(categoryId));
            log.info(">>>>> Starting test for category: " + category.getText());
            headerMenu.hoverOverMenuCategory(category);

            List<String> subcategoryIds = headerMenu.getSubcategoryIds(category);

            for (String subcategoryId : subcategoryIds) {
                category = driver.findElement(By.id(categoryId));
                headerMenu.hoverOverMenuCategory(category);

                WebElement subcategory = driver.findElement(By.id(subcategoryId));
                String expectedSubcategory = subcategory.getText();
                log.info(">>>>> Starting test for subcategory: " + expectedSubcategory);

                subcategory.click();
                String actualSubcategory = categoryPage.getOpenedCategoryTitle().toUpperCase();

                assertThat(expectedSubcategory).isEqualTo(actualSubcategory);
                log.info("Expected category name: {}, Actual category name: {}", expectedSubcategory, actualSubcategory);

                assertFilterMenuVisible();
                assertNumberOfProducts();
            }
        }
    }

    private void assertCategoryName(String category) {
        headerMenu.chooseMenuCategory(category);
        String actualCategory = categoryPage.getOpenedCategoryTitle().toUpperCase();

        assertThat(category).isEqualTo(actualCategory);

        log.info("Expected category name: {}, Actual category name: {}", category, actualCategory);
    }

    private void assertFilterMenuVisible() {
        assertTrue(at(FiltersSideMenuComponent.class).isFiltersMenuDisplayed(),
                "Filters menu is not displayed");
    }

    private void assertNumberOfProducts() {
        int totalNumberOfProducts = categoryPage.getProductCount();
        int numberOfProductMiniatures = categoryPage.getNumberOfProductMiniaturesDisplayed();

        log.info("Total number of products: " + totalNumberOfProducts + ". Number of product miniatures: " + numberOfProductMiniatures);

        assertThat(totalNumberOfProducts).isEqualTo(numberOfProductMiniatures);
    }
}