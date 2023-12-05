package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartPage extends BasePage {


    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart-items")
    private List<WebElement> shoppingCartItems;
    @FindBy(css = ".product-line-info a.label")
    private WebElement cartItem;
    @FindBy(css = ".js-cart-line-product-quantity")
    private WebElement quantityInput;


//    public void updateQuantityInCart(String productName, int totalQuantity) {
//        WebElement cartItem = findCartItemByName(productName);
//        WebElement quantityInput = cartItem.findElement(By.cssSelector(".js-cart-line-product-quantity"));
//        quantityInput.clear();
//        quantityInput.sendKeys(String.valueOf(totalQuantity));
//    }
//
//    public WebElement findCartItemByName(String productName) {
//        for (WebElement shoppingCartItem : shoppingCartItems) {
//            String cartProductName = shoppingCartItem.getText();
//            if (cartProductName.equals(productName)) {
//                return shoppingCartItem;
//            }
//        }
//        return null;
//    }


//    public boolean isProductInCart(String productName) {
//        for (WebElement shoppingCartItem : shoppingCartItems) {
//            String cartProductName = cartItem.getText();
//            if (cartProductName.equals(productName)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public String getCurrentQuantityInCart(String productName) {
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            return (String) js.executeScript("return arguments[0].value", quantityInput);
//    }
}