package pages.account;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import pages.OrderConfirmationPage;
import providers.UrlProvider;

@Getter
public class OrderSummaryPage extends BasePage {

    @FindBy(css = "[data-link-action='different-invoice-address']")
    private WebElement billingAddressDiffersLink;
    @FindBy(css = ".js-address-form .add-address a[href*='newAddress=invoice']")
    private WebElement addNewInvoiceAddressLink;
    @FindBy(name = "confirm-addresses")
    private WebElement continueButton;
    @FindBy(name = "confirmDeliveryOption")
    private WebElement confirmDeliveryOption;
    @FindBy(id = "payment-option-1")
    private WebElement payByCheckPaymentRadioButton;
    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement termsOfServiceCheckbox;
    @FindBy(css = "#payment-confirmation button")
    private WebElement placeOrderButton;

    public OrderSummaryPage(WebDriver driver) {
        super(driver);
    }

    public OrderSummaryPage addDifferentBillingAddress(String countryName) {
        billingAddressDiffersLink.click();
        defaultWait.until(ExpectedConditions.urlContains(UrlProvider.ADD_BILLING_ADDRESS));
        addNewInvoiceAddressLink.click();
        at(NewAddressPage.class).addNewAddress(countryName);
        continueButton.click();
        return this;
    }

    public OrderSummaryPage selectShippingMethod() {
        defaultWait.until(ExpectedConditions.urlContains(UrlProvider.ORDER_SUMMARY));
        confirmDeliveryOption.click();
        return this;
    }

    public OrderSummaryPage selectPaymentMethod() {
        payByCheckPaymentRadioButton.click();
        termsOfServiceCheckbox.click();
        return this;
    }

    public OrderConfirmationPage placeOrder() {
        placeOrderButton.click();
        return new OrderConfirmationPage(driver);
    }
}
