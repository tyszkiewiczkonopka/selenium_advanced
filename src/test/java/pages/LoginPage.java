package pages;

import models.User;
import models.UserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    UserFactory userFactory = new UserFactory();

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "email")
    private WebElement emailInput;
    @FindBy(name = "password")
    private WebElement passwordInput;
    @FindBy(css = "button#submit-login")
    private WebElement signInButton;

    public void login(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInButton.click();
    }

    public void loginAsRegisteredUser() {
        User registeredUser = userFactory.getAlreadyRegisteredUser();
        emailInput.sendKeys(registeredUser.getEmail());
        passwordInput.sendKeys(registeredUser.getPassword());
        signInButton.click();

    }

}
