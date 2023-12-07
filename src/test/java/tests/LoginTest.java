package tests;

import lombok.extern.slf4j.Slf4j;
import models.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.OrderConfirmationPage;
import pages.ProductPage;
import pages.ShoppingCartPage;
import pages.account.*;
import pages.components.ProductMiniatureComponent;
import pages.components.cart.AddToCartPopupComponent;
import providers.UrlProvider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class LoginTest extends BaseTest {
    static UserFactory userFactory;
    LoginPage loginPage;
    NewAddressPage newAddressPage;
    ProductMiniatureComponent productMiniatureComponent;
    ProductPage productPage;
    AddToCartPopupComponent addToCartPopupComponent;
    ShoppingCartPage shoppingCartPage;
    OpenOrderDetailsPage openOrderDetailsPage;
    OrderConfirmationPage orderConfirmationPage;
    OrderHistoryPage orderHistoryPage;
    PlacedOrderDetailsPage placedOrderDetailsPage;

    @BeforeEach
    public void setUpLoginTest() {
        loginPage = new LoginPage(driver);
        newAddressPage = new NewAddressPage(driver);
        productMiniatureComponent = new ProductMiniatureComponent(driver);
        productPage = new ProductPage(driver);
        addToCartPopupComponent = new AddToCartPopupComponent(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        openOrderDetailsPage = new OpenOrderDetailsPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);
        placedOrderDetailsPage = new PlacedOrderDetailsPage(driver);
    }

    @Test
    void test() {
        login();
        driver.get(UrlProvider.APP);
        String desiredProductName = "THE BEST IS YET POSTER";
        productMiniatureComponent.openProductView(desiredProductName);
        productPage.clickAddToCart();
        addToCartPopupComponent.proceedToCheckout();
        shoppingCartPage.proceedToCheckout();
        openOrderDetailsPage
                .addDifferentBillingAddress("Poland")
                .selectShippingMethod()
                .selectPaymentMethod()
                .placeOrder();

        String orderReference = orderConfirmationPage.extractOrderReference();
        Double totalPrice = orderConfirmationPage.extractOrderTotal();
        String orderStatus = "Awaiting check payment";

        driver.get(UrlProvider.ORDER_HISTORY_AND_DETAILS);
        orderHistoryPage.openOrderDetails(orderReference);
        assertOrderDateIsToday();
        assertTotalPrice(totalPrice);
        assertOrderStatus(orderStatus);
    }

    private void login() {
        BaseTest.driver.get(UrlProvider.LOGIN);
        userFactory = new UserFactory();
        loginPage.loginAsRegisteredUser();
    }

    private void assertOrderDateIsToday() {
        String today = String.valueOf(LocalDate.now());
        String orderDate = placedOrderDetailsPage.getOrderDate();
        LocalDate parsedDate = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        assertThat(parsedDate).isEqualTo(today);
        log.info("Actual date: " + today + ". Expected date: " + parsedDate);
    }

    private void assertTotalPrice(Double totalPriceFromOrderSummary) {
        final double totalPriceFromOrderHistory = placedOrderDetailsPage.extractOrderTotal();
        assertThat(totalPriceFromOrderHistory).isEqualTo(totalPriceFromOrderSummary);
        log.info("Actual total price: " + totalPriceFromOrderHistory + ". Expected total price: " + totalPriceFromOrderSummary);

    }

    private void assertOrderStatus(String orderStatus) {
        assertThat(placedOrderDetailsPage.getOrderStatus()).isEqualTo(orderStatus);
        log.info("Actual order status: " + placedOrderDetailsPage.getOrderStatus() + ". Expected order status: " + orderStatus);

    }


}
