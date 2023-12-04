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
    void productAdded() {
        driver.get(UrlProvider.CATEGORY_ART);
        String desiredProductName = "THE BEST IS YET POSTER";
        String desiredProductQuantityText = "3";
        String expectedProductPriceText = productMiniatureComponent.getProductPrice();
        double productPrice = getPriceFromText(expectedProductPriceText);
        int productQuantity = getQuantityFromText(desiredProductQuantityText);

        openProductView(desiredProductName);
        setProductQuantity(desiredProductQuantityText);
        productPage.addToCart();
        verifyProductNameInPopup(desiredProductName);
        verifyProductPriceInPopup(expectedProductPriceText);
        verifyProductQuantityInPopup(desiredProductQuantityText);
        verifyCartItemCountInPopup(desiredProductQuantityText);
        verifyProductsTotalPrice(productPrice, productQuantity);
        verifyMinicartProductsQuantity(desiredProductQuantityText);
    }


    private void verifyMinicartProductsQuantity(String desiredProductQuantity) {
        String minicartItemsNumber = minicartComponent.getCartItemsNumber();
        assertThat(minicartItemsNumber).isEqualTo(desiredProductQuantity);
        log.info("Actual minicart items count: " + minicartItemsNumber);
        log.info("Expected minicart items count: " + desiredProductQuantity);
        log.info("***********************************************************");
    }

    private void verifyProductsTotalPrice(double price, int quantity) {
        Double actualTotalPrice = addToCartPopupComponent.extractProductTotal();
        Double expectedTotalPrice = price * quantity;
        assertThat(actualTotalPrice).isEqualTo(expectedTotalPrice);
        log.info("Actual products total price: " + actualTotalPrice);
        log.info("Expected products total price: " + expectedTotalPrice);
        log.info("***********************************************************");
    }

    private void verifyCartItemCountInPopup(String desiredProductQuantity) {
        int actualCartItemCount = addToCartPopupComponent.extractCartItemCount();
        assertThat(actualCartItemCount).isEqualTo(Integer.parseInt(desiredProductQuantity));
        log.info("Actual cart items count: " + actualCartItemCount);
        log.info("Expected cart items count: " + desiredProductQuantity);
        log.info("***********************************************************");
    }

    private void verifyProductQuantityInPopup(String desiredProductQuantity) {
        String actualProductQuantity = String.valueOf(addToCartPopupComponent.extractProductQuantity());
        assertThat(actualProductQuantity).isEqualTo(desiredProductQuantity);
        log.info("Actual product quantity: " + actualProductQuantity);
        log.info("Expected product quantity: " + desiredProductQuantity);
        log.info("***********************************************************");
    }

    private void verifyProductPriceInPopup(String desiredProductPrice) {
        String actualProductPrice = addToCartPopupComponent.getProductPrice().getText();
        assertThat(actualProductPrice).isEqualTo(desiredProductPrice);
        log.info("Actual product price: " + actualProductPrice);
        log.info("Expected product price: " + desiredProductPrice);
        log.info("***********************************************************");
    }

    private void verifyProductNameInPopup(String desiredProductName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(By.id("blockcart-modal"), "class", "modal fade in"));
        String actualProductName = addToCartPopupComponent.getProductName();
        assertThat(actualProductName).isEqualTo(desiredProductName);
        log.info("Actual product name: " + actualProductName);
        log.info("Expected product name: " + desiredProductName);
        log.info("***********************************************************");
    }

    private void setProductQuantity(String desiredProductQuantity) {
        productPage.changeQuantity("3");
        int currentQuantity = Integer.parseInt(productPage.getCurrentQuantity());
        log.info("Actual product quantity: " + currentQuantity);
        log.info("Expected product quantity: " + desiredProductQuantity);
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

    private double getPriceFromText(String expectedProductPrice) {
        return Double.parseDouble(expectedProductPrice.replaceAll("[^\\d.]+", ""));
    }

    private int getQuantityFromText(String desiredProductQuantity) {
        return Integer.parseInt(desiredProductQuantity);
    }
}
