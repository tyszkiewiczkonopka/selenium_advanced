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

    CategoryPage categoryPage;
    HeaderMenuComponent headerMenuComponent;
    ProductMiniatureComponent productMiniatureComponent;
    FiltersSideMenuComponent filtersSideMenuComponent;

    @BeforeEach
    public void setUpLoginTest() {
        categoryPage = new CategoryPage(BaseTest.driver);
        headerMenuComponent = new HeaderMenuComponent(driver);
        productMiniatureComponent = new ProductMiniatureComponent(driver);
        filtersSideMenuComponent = new FiltersSideMenuComponent(driver);
    }

    @Test
    void category_names_should_be_displayed_after_entering_through_menu() {
        BaseTest.driver.get(UrlProvider.APP);
        List<String> menuCategories = headerMenuComponent.getAllCategoryNames();

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
        List<String> menuCategoryIds = headerMenuComponent.getAllCategoryIds();

        for (String categoryId : menuCategoryIds) {
            WebElement category = driver.findElement(By.id(categoryId));
            log.info(">>>>> Starting test for category: " + category.getText());
            headerMenuComponent.hoverOverMenuCategory(category);

            List<String> subcategoryIds = headerMenuComponent.getSubcategoryIds(category);

            for (String subcategoryId : subcategoryIds) {
                category = driver.findElement(By.id(categoryId));
                headerMenuComponent.hoverOverMenuCategory(category);

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
        headerMenuComponent.chooseMenuCategory(category);
        String actualCategory = categoryPage.getOpenedCategoryTitle().toUpperCase();
        log.info("Expected category name: {}, Actual category name: {}", category, actualCategory);
        assertThat(category).isEqualTo(actualCategory);
    }

    private void assertFilterMenuVisible() {
        assertTrue(filtersSideMenuComponent.isFiltersMenuDisplayed(), "Filters menu is not displayed");
    }

    private void assertNumberOfProducts() {
        int totalNumberOfProducts = categoryPage.getProductCount();
        int numberOfProductMiniatures = categoryPage.getNumberOfProductMiniaturesDisplayed();
        log.info("Total number of products: " + totalNumberOfProducts + ". Number of product miniatures: " + numberOfProductMiniatures);
        assertThat(totalNumberOfProducts).isEqualTo(numberOfProductMiniatures);
    }
}