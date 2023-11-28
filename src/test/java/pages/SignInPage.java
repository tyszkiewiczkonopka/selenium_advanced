package pages;

import models.User;
import models.UserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    static UserFactory userFactory;
    public SignInPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    @FindBy(name = "firstname")
    private WebElement firstNameInput;
    @FindBy (name = "lastname")
    private WebElement lastNameInput;
    @FindBy(name = "email")
    private WebElement emailInput;
    @FindBy(name = "password")
    private WebElement passwordInput;

    public void createRandomUser(){
        User randomUser = userFactory.getRandomUser().build();
        firstNameInput.sendKeys(randomUser.getFirstName());
        lastNameInput.sendKeys(randomUser.getLastName());
        emailInput.sendKeys(randomUser.getEmail());
        passwordInput.sendKeys(randomUser.getPassword());
    }
}
