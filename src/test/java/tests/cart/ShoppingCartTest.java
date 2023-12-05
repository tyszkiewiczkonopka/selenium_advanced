package tests.cart;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CategoryPage;
import pages.ProductPage;
import pages.components.ProductMiniatureComponent;
import pages.components.cart.AddToCartPopupComponent;
import pages.components.header.MinicartComponent;
import providers.UrlProvider;
import tests.BaseTest;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ShoppingCartTest extends BaseTest {
    CategoryPage categoryPage;
    ProductMiniatureComponent productMiniatureComponent;
    ProductPage productPage;
    MinicartComponent minicartComponent;
    AddToCartPopupComponent addToCartPopupComponent;


    @BeforeEach
    public void setUpLoginTest() {
        categoryPage = new CategoryPage(BaseTest.driver);
        productMiniatureComponent = new ProductMiniatureComponent(driver);
        productPage = new ProductPage(driver);
        minicartComponent = new MinicartComponent(driver);
        addToCartPopupComponent = new AddToCartPopupComponent(driver);
    }

    @Test
    void product_added_to_the_cart_should_have_the_same_data_in_popup_summary_as_on_product_page() {
        driver.get(UrlProvider.CATEGORY_ART);
        String desiredProductName = "THE BEST IS YET POSTER";
        openProductView(desiredProductName);

        double expectedProductPrice = productPage.extractProductPriceFromProductPage();
        int expectedProductQuantity = 3;

        setProductQuantity(String.valueOf(expectedProductQuantity));
        productPage.addToCart();
        verifyProductNameInPopup(desiredProductName);
        verifyProductPriceInPopup(expectedProductPrice);
        verifyProductsTotalPrice(expectedProductPrice, expectedProductQuantity);
        verifyProductQuantityInPopup(expectedProductQuantity);
        verifyCartItemCountInPopup(expectedProductQuantity);
        verifyMinicartProductsQuantity(expectedProductQuantity);
    }


    private void verifyMinicartProductsQuantity(int expectedProductQuantity) {
        String minicartItemsNumber = minicartComponent.getCartItemsNumber();
        assertThat(minicartItemsNumber).isEqualTo(String.valueOf(expectedProductQuantity));
        log.info("Actual number of items in minicart: " + minicartItemsNumber);
        log.info("Expected number of items in minicart: " + expectedProductQuantity);
        log.info("***********************************************************");
    }

    private void verifyProductsTotalPrice(double price, int quantity) {
        Double actualTotalPrice = addToCartPopupComponent.extractProductTotal();
        Double expectedTotalPrice = price * quantity;
        assertThat(actualTotalPrice).isEqualTo(expectedTotalPrice);
        log.info("Actual products subtotal: " + actualTotalPrice);
        log.info("Expected products subtotal: " + expectedTotalPrice);
        log.info("***********************************************************");
    }

    private void verifyCartItemCountInPopup(int expectedProductQuantity) {
        int actualCartItemCount = addToCartPopupComponent.extractCartItemCount();
        assertThat(actualCartItemCount).isEqualTo(expectedProductQuantity);
        log.info("Actual number of items in cart: " + actualCartItemCount);
        log.info("Expected number of items in cart: " + expectedProductQuantity);
        log.info("***********************************************************");
    }

    private void verifyProductQuantityInPopup(int expectedProductQuantity) {
        int actualProductQuantity = addToCartPopupComponent.extractProductQuantity();
        assertThat(actualProductQuantity).isEqualTo(expectedProductQuantity);
        log.info("Actual product quantity in Pop-up: " + actualProductQuantity);
        log.info("Expected product quantity in Pop-up: " + expectedProductQuantity);
        log.info("***********************************************************");
    }

    private void verifyProductPriceInPopup(double expectedProductPrice) {
        double actualProductPrice = addToCartPopupComponent.extractProductPriceFromPopup();
        assertThat(actualProductPrice).isEqualTo(expectedProductPrice);
        log.info("Actual product price in Pop-up: " + actualProductPrice);
        log.info("Expected product price in Pop-up: " + expectedProductPrice);
        log.info("***********************************************************");
    }

    private void verifyProductNameInPopup(String expectedProductName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(By.id("blockcart-modal"), "class", "modal fade in"));
        String actualProductName = addToCartPopupComponent.getProductName();
        assertThat(actualProductName).isEqualTo(expectedProductName);
        log.info("Actual product name in Pop-up: " + actualProductName);
        log.info("Expected product name in Pop-up: " + expectedProductName);
        log.info("***********************************************************");
    }

    private void setProductQuantity(String desiredProductQuantity) {
        productPage.changeQuantity(desiredProductQuantity);
        int currentQuantity = Integer.parseInt(productPage.getCurrentQuantity());
        log.info("Actual product quantity on Product Page: " + currentQuantity);
        log.info("Expected product quantity on Product Page: " + desiredProductQuantity);
        log.info("***********************************************************");
    }

    private void openProductView(String desiredProductName) {
        productMiniatureComponent.openProductView(desiredProductName);
        String actualTitle = driver.getTitle();
        assertThat(actualTitle).isEqualTo(desiredProductName);
        log.info("Actual title: " + actualTitle);
        log.info("Expected title: " + desiredProductName);
        log.info("***********************************************************");
    }
}
