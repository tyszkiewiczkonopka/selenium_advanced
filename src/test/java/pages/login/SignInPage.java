package pages.login;

import lombok.Getter;
import models.user.User;
import models.user.UserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class SignInPage extends BasePage {

    UserFactory userFactory;
    @FindBy(name = "firstname")
    private WebElement firstNameInput;
    @FindBy(name = "lastname")
    private WebElement lastNameInput;
    @FindBy(name = "email")
    private WebElement emailInput;
    @FindBy(name = "password")
    private WebElement passwordInput;

    public SignInPage(WebDriver driver) {
        super(driver);
        userFactory = new UserFactory();
    }

    public void createRandomUser() {
        User randomUser = userFactory.getRandomUser();

        firstNameInput.sendKeys(randomUser.getFirstName());
        lastNameInput.sendKeys(randomUser.getLastName());
        emailInput.sendKeys(randomUser.getEmail());
        passwordInput.sendKeys(randomUser.getPassword());
    }

}
