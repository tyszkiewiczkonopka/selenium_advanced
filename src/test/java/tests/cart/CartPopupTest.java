package tests.cart;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.CategoryPage;
import pages.ProductPage;
import pages.components.ProductMiniatureComponent;
import pages.components.cart.CartPopupComponent;
import pages.components.header.MinicartComponent;
import providers.UrlProvider;
import tests.BaseTest;

import java.math.BigDecimal;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CartPopupTest extends BaseTest {
    @Test
    void product_added_to_the_cart_should_have_the_same_data_in_popup_summary_as_on_product_page() {
        driver.get(UrlProvider.CATEGORY_ART);
        String desiredProductName = "THE BEST IS YET POSTER";
        openProductView(desiredProductName);

        BigDecimal expectedProductPrice = at(ProductPage.class).getPrice(at(ProductPage.class).getPriceInput());
        int expectedProductQuantity = 3;

        setProductQuantity(String.valueOf(expectedProductQuantity));
        at(ProductPage.class).addProduct();
        verifyProductNameInPopup(desiredProductName);
        verifyProductPriceInPopup(expectedProductPrice);
        verifyProductsTotalPrice(expectedProductPrice, expectedProductQuantity);
        verifyProductQuantityInPopup(expectedProductQuantity);
        verifyCartItemCountInPopup(expectedProductQuantity);
        verifyMinicartProductsQuantity(expectedProductQuantity);
    }


    private void verifyMinicartProductsQuantity(int expectedProductQuantity) {
        String minicartItemsNumber = at(MinicartComponent.class).getCartItemsNumber();
        assertThat(minicartItemsNumber).isEqualTo(String.valueOf(expectedProductQuantity));
        log.info("Actual number of items in minicart: " + minicartItemsNumber);
        log.info("Expected number of items in minicart: " + expectedProductQuantity);
        log.info("***********************************************************");
    }

    private void verifyProductsTotalPrice(BigDecimal price, int quantity) {
        BigDecimal actualTotalPrice = at(CartPopupComponent.class).extractProductTotal();
        BigDecimal expectedTotalPrice = price.multiply(new BigDecimal(quantity));
        assertThat(actualTotalPrice).isEqualTo(expectedTotalPrice);
        log.info("Actual products subtotal: " + actualTotalPrice);
        log.info("Expected products subtotal: " + expectedTotalPrice);
        log.info("***********************************************************");
    }

    private void verifyCartItemCountInPopup(int expectedProductQuantity) {
        int actualCartItemCount = at(CartPopupComponent.class).extractCartItemCount();
        assertThat(actualCartItemCount).isEqualTo(expectedProductQuantity);
        log.info("Actual number of items in cart: " + actualCartItemCount);
        log.info("Expected number of items in cart: " + expectedProductQuantity);
        log.info("***********************************************************");
    }

    private void verifyProductQuantityInPopup(int expectedProductQuantity) {
        int actualProductQuantity = at(CartPopupComponent.class).extractProductQuantity();
        assertThat(actualProductQuantity).isEqualTo(expectedProductQuantity);
        log.info("Actual product quantity in Pop-up: " + actualProductQuantity);
        log.info("Expected product quantity in Pop-up: " + expectedProductQuantity);
        log.info("***********************************************************");
    }

    private void verifyProductPriceInPopup(BigDecimal expectedProductPrice) {
        BigDecimal actualProductPrice = at(CartPopupComponent.class).extractProductPriceFromPopup();
        assertThat(actualProductPrice).isEqualTo(expectedProductPrice);
        log.info("Actual product price in Pop-up: " + actualProductPrice);
        log.info("Expected product price in Pop-up: " + expectedProductPrice);
        log.info("***********************************************************");
    }

    private void verifyProductNameInPopup(String expectedProductName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(By.id("blockcart-modal"), "class", "modal fade in"));
        String actualProductName = at(CartPopupComponent.class).getProductName();
        assertThat(actualProductName).isEqualTo(expectedProductName);
        log.info("Actual product name in Pop-up: " + actualProductName);
        log.info("Expected product name in Pop-up: " + expectedProductName);
        log.info("***********************************************************");
    }

    private void setProductQuantity(String desiredProductQuantity) {
        at(ProductPage.class).setQuantity(desiredProductQuantity);
        int currentQuantity = Integer.parseInt(at(ProductPage.class).getCurrentQuantity());
        log.info("Actual product quantity on Product Page: " + currentQuantity);
        log.info("Expected product quantity on Product Page: " + desiredProductQuantity);
        log.info("***********************************************************");
    }

    private void openProductView(String desiredProductName) {
        at(ProductMiniatureComponent.class).openProductView(desiredProductName);
        String actualTitle = driver.getTitle();
        assertThat(actualTitle).isEqualTo(desiredProductName);
        log.info("Actual title: " + actualTitle);
        log.info("Expected title: " + desiredProductName);
        log.info("***********************************************************");
    }
}
