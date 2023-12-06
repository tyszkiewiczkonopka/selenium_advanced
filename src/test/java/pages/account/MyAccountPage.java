package pages.account;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import providers.UrlProvider;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }
    public void goToAddFirstAddressPage(){
        driver.get(UrlProvider.ADD_FIRST_ADDRESS);
    }
}
