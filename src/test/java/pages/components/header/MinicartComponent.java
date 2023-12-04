package pages.components.header;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class MinicartComponent extends BasePage {
    public MinicartComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart-preview .cart-products-count")
    private WebElement minicartProductQuantity;

    public String getCartItemsNumber(){
        String cartItems = minicartProductQuantity.getText();
        return cartItems.replaceAll("[^0-9]", "");
    }
}
