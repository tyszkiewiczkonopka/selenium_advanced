package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
@Slf4j
public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart-items")
    private List<WebElement> shoppingCartItems;
    @FindBy(css = ".product-line-info a.label")
    private WebElement cartItem;
    @FindBy(css = ".js-cart-line-product-quantity")
    private WebElement quantityInput;
    @FindBy(css = ".cart-summary a")
    private WebElement proceedToCheckoutButton;

    public void proceedToCheckout() {
        defaultWait.until(ExpectedConditions.visibilityOf(proceedToCheckoutButton));
        proceedToCheckoutButton.click();
    }


//    public void updateQuantityInCart(int totalQuantity) {
//        quantityInput.clear();
//        quantityInput.sendKeys(String.valueOf(totalQuantity));
//    }


//    public String getCurrentQuantityInCart(String productName) {
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            return (String) js.executeScript("return arguments[0].value", quantityInput);
//    }


//    public List<WebElement> getAllShoppingCartItems(){
//        log.info("All products in the cart: " + shoppingCartItems.toString());
//            return shoppingCartItems;
//    }

}