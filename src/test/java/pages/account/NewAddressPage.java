package pages.account;

import lombok.extern.slf4j.Slf4j;
import models.address.Address;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

@Slf4j
public class NewAddressPage extends BasePage {

    @FindBy(name = "address1")
    private WebElement addressInput;
    @FindBy(name = "city")
    private WebElement cityInput;
    @FindBy(name = "postcode")
    private WebElement postcodeInput;
    @FindBy(name = "id_country")
    private WebElement countryDropdown;

    public NewAddressPage(WebDriver driver) {
        super(driver);
    }

    public void addNewAddress(Address newAddress, String countryName) {
        addressInput.sendKeys(newAddress.getAddress());
        cityInput.sendKeys(newAddress.getCity());
        postcodeInput.sendKeys(newAddress.getPostcode());
        selectVisibleText(countryDropdown, countryName);

        log.info(newAddress.toString());
    }


}
