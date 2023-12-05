package tests.cart;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CategoryPage;
import pages.ProductPage;
import pages.ShoppingCartPage;
import pages.components.ProductMiniatureComponent;
import providers.UrlProvider;
import tests.BaseTest;

import java.util.Random;

@Slf4j
public class GenericRandomProductsTest extends BaseTest {
    CategoryPage categoryPage;
    ProductMiniatureComponent productMiniatureComponent;
    ProductPage productPage;
    ShoppingCartPage shoppingCartPage;

    @BeforeEach
    public void setUpLoginTest() {
        categoryPage = new CategoryPage(BaseTest.driver);
        productMiniatureComponent = new ProductMiniatureComponent(driver);
        productPage = new ProductPage(driver);
    }

    @Test
    public void genericCart() {

    }

    private int getRandomQuantity() {
        return new Random().nextInt(5) + 1;
    }


}
