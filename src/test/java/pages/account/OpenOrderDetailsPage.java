package pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import providers.UrlProvider;

import java.util.List;

public class OpenOrderDetailsPage extends BasePage {
    NewAddressPage newAddressPage;

    public OpenOrderDetailsPage(WebDriver driver) {
        super(driver);
        newAddressPage = new NewAddressPage(driver);
    }

    @FindBy(css = "[data-link-action='different-invoice-address']")
    private WebElement billingAddressDiffersLink;
    @FindBy(css = ".js-address-form .add-address a[href*='newAddress=invoice']")
    private WebElement addNewInvoiceAddressLink;
    @FindBy(name = "confirm-addresses")
    private WebElement continueButton;
//    @FindBy(css = ".delivery-options input[type='radio']")
//    private List<WebElement> deliveryOptionsRadioButtons;
    @FindBy(name = "confirmDeliveryOption")
    private WebElement confirmDeliveryOption;
    @FindBy(id = "payment-option-1")
    private WebElement payByCheckPaymentRadioButton;
    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement termsOfServiceCheckbox;
    @FindBy(css = "#payment-confirmation button")
    private WebElement placeOrderButton;

    public OpenOrderDetailsPage addDifferentBillingAddress(String countryName) {
        billingAddressDiffersLink.click();
        defaultWait.until(ExpectedConditions.urlContains(UrlProvider.ADD_BILLING_ADDRESS));
        addNewInvoiceAddressLink.click();

        newAddressPage.addNewAddress(countryName);
        continueButton.click();
        return this;
    }
    public OpenOrderDetailsPage selectShippingMethod() {
        defaultWait.until(ExpectedConditions.urlContains(UrlProvider.ORDER_SUMMARY));
        confirmDeliveryOption.click();
        return this;
    }
    public OpenOrderDetailsPage selectPaymentMethod(){
        payByCheckPaymentRadioButton.click();
        termsOfServiceCheckbox.click();
        return this;
    }
    public void placeOrder(){
        placeOrderButton.click();
    }
}
