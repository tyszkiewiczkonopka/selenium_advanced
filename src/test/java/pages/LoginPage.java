package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;
    @FindBy(id = "submit-login")
    private WebElement signInButton;

    public void login(String email, String password){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInButton.click();
    }

}
