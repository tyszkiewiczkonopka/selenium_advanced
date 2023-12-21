package tests.cart;

import lombok.extern.slf4j.Slf4j;
import models.cart.Cart;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.cart.CartPage;
import providers.url.UrlProvider;
import tests.base.BaseTest;

import java.math.BigDecimal;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class RemoveProductsFromCartTest extends BaseTest {
    final int NUMBER_OF_PRODUCTS = 2;

    @Test
    public void products_should_be_successfully_removed_from_cart_with_updated_total() {
        driver.get(UrlProvider.APP);
        Cart cart = new Cart();

        for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
            at(CartPage.class).addRandomProductToCart(cart);
        }

        BigDecimal cartTotal = cart.calculateTotal(cart.getAllCartLines());
        driver.get((UrlProvider.CART));
        assertTotalValuesAndProductCount(cartTotal);
        removeProductLine(cart);

        BigDecimal newCartTotal = cart.calculateTotal(cart.getAllCartLines());
        assertTotalValuesAndProductCount(newCartTotal);
        removeProductLine(cart);

        assertThat(at(CartPage.class).getEmptyCartLabel().getText()).isEqualTo("There are no more items in your cart");
        log.info("Number of products on Cart Page: " + at(CartPage.class).getCartLines().size());
    }

    public void assertTotalValuesAndProductCount(BigDecimal cartTotal) {
        BigDecimal shippingCost = at(CartPage.class).getShippingCost();
        BigDecimal cartPageTotal = at(CartPage.class).getCartTotal();

        assertThat(cartPageTotal.subtract(shippingCost)).isEqualTo(cartTotal);
        log.info("Number of products on Cart Page: " + at(CartPage.class).getCartLines().size());
        log.info("Expected total: " + cartTotal);
        log.info("Actual total: " + cartPageTotal.subtract(shippingCost));
    }

    private void removeProductLine(Cart cart) {
        int productLinesInCart = at(CartPage.class).getNumberOfLinesInCart();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        at(CartPage.class).clickTrashIcon();
        cart.removeFirstCartLine();

        if (productLinesInCart > 1) {
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".cart-item"), productLinesInCart - 1));
        } else {
            wait.until(ExpectedConditions.textToBePresentInElement(at(CartPage.class).getEmptyCartLabel(), "There are no more items in your cart"));
        }

    }
}

