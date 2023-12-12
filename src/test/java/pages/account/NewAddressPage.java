package pages.account;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import models.Address;
import models.AddressFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

@Slf4j
@Getter
public class NewAddressPage extends BasePage {
    AddressFactory addressFactory;

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
        addressFactory = new AddressFactory();
    }

    public void addNewAddress(String countryName) {
        Address newAddress = addressFactory.getRandomAddress();

        addressInput.sendKeys(newAddress.getAddress());
        cityInput.sendKeys(newAddress.getCity());
        postcodeInput.sendKeys(newAddress.getPostcode());
        chooseCountry(countryName);

        log.info(newAddress.toString());
    }


    private void chooseCountry(String countryName) {
        Select select = new Select(countryDropdown);
        select.selectByVisibleText(countryName);
    }
}
