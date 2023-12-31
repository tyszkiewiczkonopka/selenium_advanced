package tests.cart;

import lombok.extern.slf4j.Slf4j;
import models.cart.Cart;
import org.junit.jupiter.api.Test;
import pages.cart.CartPopupComponent;
import pages.common.header.MiniCartComponent;
import pages.common.productMiniature.ProductMiniatureComponent;
import pages.product.ProductPage;
import providers.url.UrlProvider;
import tests.base.BaseTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CartPopupTest extends BaseTest {

    @Test
    void product_added_to_the_cart_should_have_the_same_data_in_popup_summary_as_on_product_page() {
        driver.get(UrlProvider.CATEGORY_ART);
        String desiredProductName = "THE BEST IS YET POSTER";
        ProductPage desiredProduct = at(ProductMiniatureComponent.class).openProductView(desiredProductName);
        BigDecimal expectedProductPrice = desiredProduct.getProductPrice();
        int expectedProductQuantity = 3;

        at(ProductPage.class).setQuantity(String.valueOf(expectedProductQuantity));
        Cart cart = new Cart();
        cart.addCartLine(desiredProduct.toCartLine());
        at(ProductPage.class).addProduct();

        assertProductNameInPopup(desiredProductName);
        assertProductPriceInPopup(expectedProductPrice);
        assertProductsTotalPrice(expectedProductPrice, expectedProductQuantity);
        assertProductQuantityInPopup(expectedProductQuantity);
        assertCartItemCountInPopup(expectedProductQuantity);
        assertMiniCartProductsQuantity(expectedProductQuantity);
    }


    private void assertMiniCartProductsQuantity(int expectedProductQuantity) {
        String miniCartItemsNumber = at(MiniCartComponent.class).getCartItemsNumber();

        assertThat(miniCartItemsNumber).isEqualTo(String.valueOf(expectedProductQuantity));
        log.info("Actual number of items in minicart: " + miniCartItemsNumber);
        log.info("Expected number of items in minicart: " + expectedProductQuantity);
        log.info("***********************************************************");
    }

    private void assertProductsTotalPrice(BigDecimal price, int quantity) {
        BigDecimal actualTotalPrice = at(CartPopupComponent.class).getAllProductsPrice();
        BigDecimal expectedTotalPrice = price.multiply(new BigDecimal(quantity));

        assertThat(actualTotalPrice).isEqualTo(expectedTotalPrice);
        log.info("Actual products subtotal: " + actualTotalPrice);
        log.info("Expected products subtotal: " + expectedTotalPrice);
        log.info("***********************************************************");
    }

    private void assertCartItemCountInPopup(int expectedProductQuantity) {
        int actualCartItemCount = at(CartPopupComponent.class).extractCartItemCount();

        assertThat(actualCartItemCount).isEqualTo(expectedProductQuantity);
        log.info("Actual number of items in cart: " + actualCartItemCount);
        log.info("Expected number of items in cart: " + expectedProductQuantity);
        log.info("***********************************************************");
    }

    private void assertProductQuantityInPopup(int expectedProductQuantity) {
        int actualProductQuantity = at(CartPopupComponent.class).extractProductQuantity();

        assertThat(actualProductQuantity).isEqualTo(expectedProductQuantity);
        log.info("Actual product quantity in Pop-up: " + actualProductQuantity);
        log.info("Expected product quantity in Pop-up: " + expectedProductQuantity);
        log.info("***********************************************************");
    }

    private void assertProductPriceInPopup(BigDecimal expectedProductPrice) {
        BigDecimal actualProductPrice = at(CartPopupComponent.class).getProductPrice();

        assertThat(actualProductPrice).isEqualTo(expectedProductPrice);
        log.info("Actual product price in Pop-up: " + actualProductPrice);
        log.info("Expected product price in Pop-up: " + expectedProductPrice);
        log.info("***********************************************************");
    }

    private void assertProductNameInPopup(String expectedProductName) {
        at(CartPopupComponent.class).waitUntilModalFadesIn();
        String actualProductName = at(CartPopupComponent.class).getProductName();

        assertThat(actualProductName).isEqualTo(expectedProductName);
        log.info("Actual product name in Pop-up: " + actualProductName);
        log.info("Expected product name in Pop-up: " + expectedProductName);
        log.info("***********************************************************");
    }

}
