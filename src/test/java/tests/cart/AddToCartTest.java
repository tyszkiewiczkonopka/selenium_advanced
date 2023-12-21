package tests.cart;

import lombok.extern.slf4j.Slf4j;
import models.cart.Cart;
import models.cart.CartLine;
import org.junit.jupiter.api.Test;
import pages.cart.CartPage;
import providers.url.UrlProvider;
import tests.base.BaseTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AddToCartTest extends BaseTest {
    final int NUMBER_OF_PRODUCTS = 10;
    @Test
    public void products_added_to_cart_should_give_correct_total() {
        driver.get(UrlProvider.APP);

        Cart cart = new Cart();

        for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
            at(CartPage.class).addRandomProductToCart(cart);
        }

        BigDecimal expectedTotal = cart.calculateTotal(cart.getAllCartLines());

        driver.get(UrlProvider.CART);

        List<CartLine> currentCartLines = at(CartPage.class).getCartLines();
        BigDecimal cartTotal = at(CartPage.class).getCartTotal();
        BigDecimal shippingCost = at(CartPage.class).getShippingCost();
        BigDecimal currentTotal = cartTotal.subtract(shippingCost);

        assertThat(currentCartLines)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(cart.getAllCartLines());

        assertThat(currentTotal).isEqualByComparingTo(expectedTotal);
        log.info("Current products total: " + currentTotal + ". Expected products total: " + expectedTotal);
    }
}
