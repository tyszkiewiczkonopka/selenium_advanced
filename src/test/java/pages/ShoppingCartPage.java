package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartPage extends BasePage {


    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart-items")
    List<WebElement> shoppingCartItems;


}
