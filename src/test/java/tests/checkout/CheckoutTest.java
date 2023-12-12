package tests.checkout;

import lombok.extern.slf4j.Slf4j;
import models.UserFactory;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.OrderConfirmationPage;
import pages.account.OrderSummaryPage;
import pages.account.OrdersHistoryPage;
import pages.account.PlacedOrderDetailsPage;
import pages.components.ProductMiniatureComponent;
import pages.components.cart.CartPopupComponent;
import providers.UrlProvider;
import tests.BaseTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CheckoutTest extends BaseTest {
    UserFactory userFactory;

    OrderConfirmationPage orderConfirmationPage = at(OrderConfirmationPage.class);
    PlacedOrderDetailsPage placedOrderDetailsPage = at(PlacedOrderDetailsPage.class);

    @Test
    void order_details_from_order_should_match_details_in_order_history() {
        login();
        driver.get(UrlProvider.APP);
        String desiredProductName = "THE BEST IS YET POSTER";
        at(ProductMiniatureComponent.class)
                .openProductView(desiredProductName)
                .addProduct();
        at(CartPopupComponent.class)
                .proceedToCheckout()
                .proceedToCheckout();
        at(OrderSummaryPage.class)
                .addDifferentBillingAddress("Poland")
                .selectShippingMethod()
                .selectPaymentMethod()
                .placeOrder();


        String expectedOrderReference = orderConfirmationPage.extractOrderReference();
        BigDecimal expectedTotalPrice = orderConfirmationPage.getPrice(orderConfirmationPage.getOrderTotalLabel());
        String expectedOrderStatus = "Awaiting check payment";

        driver.get(UrlProvider.ORDER_HISTORY_AND_DETAILS);
        at(OrdersHistoryPage.class)
                .openOrderDetails(expectedOrderReference);

        assertOrderDateIsToday();
        assertTotalPrice(expectedTotalPrice);
        assertOrderStatus(expectedOrderStatus);
    }

    private void login() {
        BaseTest.driver.get(UrlProvider.LOGIN);
        userFactory = new UserFactory();
        at(LoginPage.class).loginAsRegisteredUser();
    }

    private void assertOrderDateIsToday() {
        String today = String.valueOf(LocalDate.now());
        String orderDate = placedOrderDetailsPage.getText(placedOrderDetailsPage.getOrderDateLabel());
        LocalDate parsedDate = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        assertThat(parsedDate).isEqualTo(today);
        log.info("Actual date: " + today + ". Expected date: " + parsedDate);
    }

    private void assertTotalPrice(BigDecimal expectedTotalPrice) {
        BigDecimal totalPriceFromOrderHistory = placedOrderDetailsPage.getPrice(placedOrderDetailsPage.getOrderTotalLabel());
        assertThat(totalPriceFromOrderHistory).isEqualTo(expectedTotalPrice);
        log.info("Actual total price: " + totalPriceFromOrderHistory + ". Expected total price: " + expectedTotalPrice);

    }

    private void assertOrderStatus(String expectedOrderStatus) {
        assertThat(placedOrderDetailsPage.getText(placedOrderDetailsPage.getOrderStatusLabel()))
                .isEqualTo(expectedOrderStatus);

        log.info("Actual order status: " + placedOrderDetailsPage.getOrderStatusLabel() + ". Expected order status: " + expectedOrderStatus);
    }
}
