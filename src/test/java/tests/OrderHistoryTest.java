package tests;

import lombok.extern.slf4j.Slf4j;
import models.UserFactory;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.OrderConfirmationPage;
import pages.ProductPage;
import pages.account.OpenOrderDetailsPage;
import pages.account.OrderHistoryPage;
import pages.account.PlacedOrderDetailsPage;
import pages.components.ProductMiniatureComponent;
import pages.components.cart.CartPopupComponent;
import providers.UrlProvider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class OrderHistoryTest extends BaseTest {
    UserFactory userFactory;

    @Test
    void order_details_from_order_should_match_details_in_order_history() {
        login();
        driver.get(UrlProvider.APP);
        String desiredProductName = "THE BEST IS YET POSTER";
        at(ProductMiniatureComponent.class).openProductView(desiredProductName);
        at(ProductPage.class).addProduct();
        at(CartPopupComponent.class).proceedToCheckout();
        at(CartPage.class).proceedToCheckout();
        at(OpenOrderDetailsPage.class)
                .addDifferentBillingAddress("Poland")
                .selectShippingMethod()
                .selectPaymentMethod()
                .placeOrder();

        String orderReference = at(OrderConfirmationPage.class).extractOrderReference();
        Double totalPrice = at(OrderConfirmationPage.class).extractOrderTotal();
        String orderStatus = "Awaiting check payment";

        driver.get(UrlProvider.ORDER_HISTORY_AND_DETAILS);
        at(OrderHistoryPage.class).openOrderDetails(orderReference);
        assertOrderDateIsToday();
        assertTotalPrice(totalPrice);
        assertOrderStatus(orderStatus);
    }

    private void login() {
        BaseTest.driver.get(UrlProvider.LOGIN);
        userFactory = new UserFactory();
        at(LoginPage.class).loginAsRegisteredUser();
    }

    private void assertOrderDateIsToday() {
        String today = String.valueOf(LocalDate.now());
        String orderDate = at(PlacedOrderDetailsPage.class).getOrderDate();
        LocalDate parsedDate = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        assertThat(parsedDate).isEqualTo(today);
        log.info("Actual date: " + today + ". Expected date: " + parsedDate);
    }

    private void assertTotalPrice(Double totalPriceFromOrderSummary) {
        final double totalPriceFromOrderHistory = at(PlacedOrderDetailsPage.class).extractOrderTotal();
        assertThat(totalPriceFromOrderHistory).isEqualTo(totalPriceFromOrderSummary);
        log.info("Actual total price: " + totalPriceFromOrderHistory + ". Expected total price: " + totalPriceFromOrderSummary);

    }

    private void assertOrderStatus(String orderStatus) {
        assertThat(at(PlacedOrderDetailsPage.class).getOrderStatus()).isEqualTo(orderStatus);
        log.info("Actual order status: " + at(PlacedOrderDetailsPage.class).getOrderStatus() + ". Expected order status: " + orderStatus);

    }


}
