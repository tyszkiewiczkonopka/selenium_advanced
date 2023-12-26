package pages.login;

import models.user.User;
import models.user.UserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class LoginPage extends BasePage {
    UserFactory userFactory = new UserFactory();

    @FindBy(name = "email")
    private WebElement emailInput;
    @FindBy(name = "password")
    private WebElement passwordInput;
    @FindBy(css = "button#submit-login")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginAsRegisteredUser() {
        User registeredUser = userFactory.getAlreadyRegisteredUser();
        emailInput.sendKeys(registeredUser.getEmail());
        passwordInput.sendKeys(registeredUser.getPassword());
        signInButton.click();

    }

}
