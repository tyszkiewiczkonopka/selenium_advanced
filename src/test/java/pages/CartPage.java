package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import models.Cart;
import models.CartLine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.account.OrderSummaryPage;
import pages.components.ProductMiniatureComponent;
import pages.components.cart.CartLineComponent;

import java.util.List;

@Slf4j
@Getter
public class CartPage extends BasePage {
    @FindBy(css = ".cart-item")
    private List<WebElement> shoppingCartItems;
    @FindBy(css = ".cart-items")
    private WebElement cartItemsList;
    @FindBy(css = ".product-line-info a.label")
    private WebElement cartItem;
    @FindBy(css = ".js-cart-line-product-quantity")
    private WebElement quantityInput;
    @FindBy(css = ".cart-summary a")
    private WebElement proceedToCheckoutButton;
    @FindBy(css = ".cart-total .value")
    private WebElement cartTotalLabel;
    @FindBy(id = "cart-subtotal-shipping")
    private WebElement shippingCostLabel;
    @FindBy(xpath = ".//i[.='delete']")
    private WebElement trashIcon;
    @FindBy(css = ".cart-overview.js-cart")
    private WebElement emptyCartLabel;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<CartLine> getCartLines() {
        List<CartLine> cartLines = getShoppingCartItems().stream()
                .map((WebElement element) -> new CartLineComponent(driver, element).toCartLine())
                .toList();

        for (CartLine cartLine : cartLines) {
            log.info(cartLine.toString());
        }

        return cartLines;
    }


    public OrderSummaryPage proceedToCheckout() {
        defaultWait.until(ExpectedConditions.visibilityOf(proceedToCheckoutButton));
        proceedToCheckoutButton.click();
        return new OrderSummaryPage(driver);
    }

    public void addRandomProductToCart(Cart cart) {
        String randomProductName = at(HomePage.class).getRandomProductName();
        int randomQuantity = at(ProductPage.class).generateRandomQuantity();
        CartLine expectedCartLine = at(ProductMiniatureComponent.class)
                .openProductView(randomProductName)
                .setQuantity(String.valueOf(randomQuantity))
                .toCartLine();

        at(ProductPage.class).addProduct();
        cart.addCartLine(expectedCartLine);

        driver.navigate().back();
    }


}
