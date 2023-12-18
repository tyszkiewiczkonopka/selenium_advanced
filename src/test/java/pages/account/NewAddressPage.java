package pages.account;

import lombok.extern.slf4j.Slf4j;
import models.address.Address;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
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
        chooseCountry(countryName);

        log.info(newAddress.toString());
    }


    private void chooseCountry(String countryName) {
        Select select = new Select(countryDropdown);
        select.selectByVisibleText(countryName);
    }


    // TODO: CHODZI O TAKĄ ZMIANĘ?
    /*

     private void selectVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

     */
}
