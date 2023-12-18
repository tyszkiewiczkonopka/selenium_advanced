package tests.checkout;

import lombok.extern.slf4j.Slf4j;
import models.address.Address;
import models.address.AddressFactory;
import org.junit.jupiter.api.Test;
import pages.login.LoginPage;
import pages.order.OrderConfirmationPage;
import pages.account.OrderSummaryPage;
import pages.account.OrdersHistoryPage;
import pages.account.PlacedOrderDetailsPage;
import pages.common.productMiniature.ProductMiniatureComponent;
import pages.cart.CartPopupComponent;
import providers.UrlProvider;
import tests.base.BaseTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CheckoutTest extends BaseTest {
    AddressFactory addressFactory = new AddressFactory();
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

        Address newAddress = addressFactory.getRandomAddress();

        at(OrderSummaryPage.class)
                .addDifferentBillingAddress(newAddress, "Poland")
                .selectShippingMethod()
                .selectPaymentMethod()
                .placeOrder();


        String expectedOrderReference = orderConfirmationPage.extractOrderReference();
        BigDecimal expectedTotalPrice = orderConfirmationPage.getOrderTotal();
        String expectedOrderStatus = "Awaiting check payment";

        driver.get(UrlProvider.ORDER_HISTORY_AND_DETAILS);
        at(OrdersHistoryPage.class)
                .openOrderDetails(expectedOrderReference);

        assertOrderDateIsToday();
        assertTotalPrice(expectedTotalPrice);
        assertOrderStatus(expectedOrderStatus);
    }

    private void login() {
        driver.get(UrlProvider.LOGIN);
        at(LoginPage.class).loginAsRegisteredUser();
    }

    private void assertOrderDateIsToday() {
        String today = String.valueOf(LocalDate.now());
        String orderDate = placedOrderDetailsPage.getOrderDate();
        LocalDate parsedDate = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")); // TODO: to też do Base któregoś?

        assertThat(parsedDate).isEqualTo(today);
        log.info("Actual date: " + today + ". Expected date: " + parsedDate);
    }

    private void assertTotalPrice(BigDecimal expectedTotalPrice) {
        BigDecimal totalPriceFromOrderHistory = placedOrderDetailsPage.getOrderTotal();

        assertThat(totalPriceFromOrderHistory).isEqualTo(expectedTotalPrice);
        log.info("Actual total price: " + totalPriceFromOrderHistory + ". Expected total price: " + expectedTotalPrice);
    }

    private void assertOrderStatus(String expectedOrderStatus) {
        String actualOrderStatus = placedOrderDetailsPage.getOrderStatus();

        assertThat(actualOrderStatus).isEqualTo(expectedOrderStatus);
        log.info("Actual order status: " + actualOrderStatus + ". Expected order status: " + expectedOrderStatus);
    }
}
