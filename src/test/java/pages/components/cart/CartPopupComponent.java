package pages.components.cart;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.CartPage;

import java.time.Duration;

@Getter
public class CartPopupComponent extends BasePage {
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
    private WebElement allProductsPrice;
    @FindBy(css = ".cart-content-btn a")
    private WebElement proceedToCheckoutButton;

    public CartPopupComponent(WebDriver driver) {
        super(driver);
    }

    public int extractProductQuantity() {
        String productQuantityText = productQuantity.getText();
        return Integer.parseInt(productQuantityText.replaceAll("[^0-9]", ""));
    }

    public int extractCartItemCount() {
        String cartSummaryText = cartItemQuantitySummary.getText();
        return Integer.parseInt(cartSummaryText.replaceAll("[^0-9]", ""));
    }

    public CartPage proceedToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(By.id("blockcart-modal"), "class", "modal fade in"));
        proceedToCheckoutButton.click();
        return new CartPage(driver);
    }

}
