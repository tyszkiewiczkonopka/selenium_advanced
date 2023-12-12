package pages.components.header;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

@Getter
public class MiniCartComponent extends BasePage {
    @FindBy(css = ".cart-preview .cart-products-count")
    private WebElement miniCartProductQuantity;

    public MiniCartComponent(WebDriver driver) {
        super(driver);
    }

    public String getCartItemsNumber() {
        String cartItems = miniCartProductQuantity.getText();
        return cartItems.replaceAll("[^0-9]", "");
    }
}
