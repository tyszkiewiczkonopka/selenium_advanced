package tests.categories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CategoryPage;
import pages.components.HeaderMenuComponent;
import pages.components.ProductMiniatureComponent;
import providers.UrlProvider;
import tests.BaseTest;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class CategoriesTest extends BaseTest {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Actions actions;
    CategoryPage categoryPage;
    HeaderMenuComponent headerMenuComponent;
    ProductMiniatureComponent productMiniatureComponent;

    @BeforeEach
    public void setUpLoginTest() {
        categoryPage = new CategoryPage(BaseTest.driver);
        headerMenuComponent = new HeaderMenuComponent(driver);
        productMiniatureComponent = new ProductMiniatureComponent(driver);
    }

    @Test
    void main_categories_check() {
        BaseTest.driver.get(UrlProvider.APP);
        List<String> menuCategories = headerMenuComponent.getAllCategoryNames();

        for (String category : menuCategories) {
            log.info(">>>>> Starting test for category: " + category);

            headerMenuComponent.chooseMenuCategory(category);
            String actualCategory = categoryPage.getOpenedCategoryTitle().toUpperCase();
            assertThat(category).isEqualTo(actualCategory);
            log.info("Expected category name: {}, Actual category name: {}", category, actualCategory);

            assertTrue(categoryPage.isFiltersMenuDisplayed(), "Filters menu is not displayed");

            int totalNumberOfProducts = categoryPage.getProductCount();
            int numberOfProductMiniatures = categoryPage.getNumberOfProductMiniaturesDisplayed();
            log.info("Total number of products: " + totalNumberOfProducts + ". Number of product miniatures: " + numberOfProductMiniatures);
            assertThat(totalNumberOfProducts).isEqualTo(numberOfProductMiniatures);
        }
    }

    @Test
    void verifySubcategoryNamesAfterEnteringCategories() {

    }

}