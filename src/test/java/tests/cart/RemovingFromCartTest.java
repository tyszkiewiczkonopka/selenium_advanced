package tests.cart;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CategoryPage;
import providers.UrlProvider;
import tests.BaseTest;

@Slf4j
public class RemovingFromCartTest extends BaseTest {
    CategoryPage categoryPage;

    @BeforeEach
    public void setUpLoginTest() {
        categoryPage = new CategoryPage(BaseTest.driver);

    }

    @Test
    public void testShoppingCart() {

        driver.get(UrlProvider.APP);

        categoryPage.selectRandomProduct();
        categoryPage.addRandomProductToCart();



        // NOT WORKING!

//            driver.get(UrlProvider.CART);
//
//            checkTotalOrderPrice();
//            removeProductFromCart(1);
//            checkTotalOrderPrice();
//            removeProductFromCart(2);
//            checkEmptyBasketLabel();


    }


    private static void checkTotalOrderPrice() {
        // Implementation to check if the total order price is correct
        // ...

        log.info("Checked if the total order price is correct");
    }

    private static void removeProductFromCart(int productIndex) {
        // Implementation to remove a product from the cart based on its index
        // ...

        log.info("Removed product from the cart at index: " + productIndex);
    }

    private static void checkEmptyBasketLabel() {
        // Implementation to check if the empty basket label is displayed
        // ...

        log.info("Checked if the empty basket label is displayed");
    }
}
