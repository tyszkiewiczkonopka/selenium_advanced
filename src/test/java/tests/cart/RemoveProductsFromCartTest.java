package tests.cart;

import lombok.extern.slf4j.Slf4j;
import models.Cart;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CartPage;
import providers.UrlProvider;
import tests.BaseTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class RemoveProductsFromCartTest extends BaseTest {
    CartPage cartPage = at(CartPage.class);

    @RepeatedTest(1)
    public void products_should_be_successfully_removed_from_cart_with_updated_total() {
        driver.get(UrlProvider.APP);
        Cart cart = new Cart();

        for (int i = 0; i < 2; i++) {
            cartPage.addRandomProductToCart(cart);
        }

        BigDecimal cartTotal = cart.calculateTotal(cart.getAllCartLines());

        driver.get((UrlProvider.CART));

        assertTotalValuesAndProductCount(cartTotal);

        removeProductLine(cart);
        defaultWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".cart-item"), 1));

        BigDecimal newCartTotal = cart.calculateTotal(cart.getAllCartLines());
        assertTotalValuesAndProductCount(newCartTotal);

        removeProductLine(cart);
        defaultWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".cart-item"), 0));

        assertThat(cartPage.getEmptyCartLabel().getText()).isEqualTo("There are no more items in your cart");
        log.info("Number of products on Cart Page: " + cartPage.getCartLines().size());
    }

    public void assertTotalValuesAndProductCount(BigDecimal cartTotal) {
        BigDecimal shippingCost = cartPage.getPrice(cartPage.getShippingCostLabel());
        BigDecimal cartPageTotal = cartPage.getPrice(cartPage.getCartTotalLabel());

        assertThat(cartPageTotal.subtract(shippingCost)).isEqualTo(cartTotal);
        log.info("Number of products on Cart Page: " + cartPage.getCartLines().size());
        log.info("Expected total: " + cartTotal);
        log.info("Actual total: " + cartPageTotal.subtract(shippingCost));
    }

    private void removeProductLine(Cart cart) {
        cartPage.getTrashIcon().click();
        cart.removeFirstCartLine();
    }

}
