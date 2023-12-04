package pages.components.cart;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

@Getter
public class AddToCartPopupComponent extends BasePage {
    public AddToCartPopupComponent(WebDriver driver) {
        super(driver);
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

}
