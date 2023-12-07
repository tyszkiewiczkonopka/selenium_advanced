package pages.components.cart;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.ShoppingCartPage;

import java.time.Duration;

@Getter
public class AddToCartPopupComponent extends BasePage {
    ShoppingCartPage shoppingCartPage;

    public AddToCartPopupComponent(WebDriver driver) {
        super(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @FindBy(css = ".modal.fade.in .h6.product-name")
    private WebElement productName;
    @FindBy(css = "[class='col-md-5 divide-right'] .product-price")
    private WebElement productPrice;
    @FindBy(css = "[class='col-md-5 divide-right'] .product-quantity")
    private WebElement productQuantity;
    @FindBy(css = ".cart-content .cart-products-count")
    private WebElement cartItemQuantitySummary;
    @FindBy(css = ".product-total .value")
    private WebElement totalPrice;
    @FindBy(css = ".subtotal.value")
    private WebElement subtotalValue;
    @FindBy(css = ".cart-content-btn a")
    private WebElement proceedToCheckoutButton;

    public String getProductName(){
        return productName.getText();
    }
    public int extractProductQuantity(){
        String productQuantityText = productQuantity.getText();
        return Integer.parseInt(productQuantityText.replaceAll("[^0-9]", ""));
    }

    public int extractCartItemCount() {
        String cartSummaryText = cartItemQuantitySummary.getText();
        return Integer.parseInt(cartSummaryText.replaceAll("[^0-9]", ""));
    }

    public double extractProductTotal() {
        String subtotalProductsPrice = subtotalValue.getText();
        return Double.parseDouble(subtotalProductsPrice.replaceAll("[^\\d.]", ""));
    }
    public double extractProductPriceFromPopup(){
        String productPrice = getProductPrice().getText();
        return Double.parseDouble(productPrice.replaceAll("[^\\d.]", ""));
    }
    public void proceedToCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(By.id("blockcart-modal"), "class", "modal fade in"));
        proceedToCheckoutButton.click();
    }
    public boolean isProductInCart(String productName) {
        for (WebElement shoppingCartItem : shoppingCartPage.getAllShoppingCartItems()) {
            String cartProductName = shoppingCartItem.getText();
            if (cartProductName.equals(productName)) {
                return true;
            }
        }
        return false;
    }
//    public void assertProductAddedOrQuantityUpdated(String productName, int addToCartQuantity){
//        if(isProductInCart(productName)){
//            String currentQuantityInCart = shoppingCartPage.getCurrentQuantityInCart(productName);
//            String increasedQuantityInCart = currentQuantityInCart + addToCartQuantity;
//            shoppingCartPage.updateQuantityInCart(Integer.parseInt(increasedQuantityInCart));
//        } else {
//
//        }
//    }
}
