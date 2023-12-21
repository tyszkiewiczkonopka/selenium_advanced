package pages.cart;

import lombok.extern.slf4j.Slf4j;
import models.cart.Cart;
import models.cart.CartLine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.account.OrderSummaryPage;
import pages.base.BasePage;
import pages.common.productMiniature.ProductMiniatureComponent;
import pages.exceptions.NoCartLinesFoundException;
import pages.home.HomePage;
import pages.product.ProductPage;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
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

    private List<WebElement> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public List<CartLine> getCartLines() {

        List<WebElement> shoppingCartItems = getShoppingCartItems();

        if (shoppingCartItems.isEmpty()) {
            throw new NoCartLinesFoundException();
        }

        return getShoppingCartItems().stream()
                .map((WebElement element) -> {
                            CartLine cartLine = new CartLineComponent(driver, element).toCartLine();
                            log.info(cartLine.toString());
                            return cartLine;
                        }
                )
                .toList();
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

    public int getNumberOfLinesInCart() {
        return getCartLines().size();
    }

    public WebElement getEmptyCartLabel() {
        return emptyCartLabel;
    }

    public BigDecimal getCartTotal() {
        return getPrice(cartTotalLabel);
    }

    public BigDecimal getShippingCost() {
        return getPrice(shippingCostLabel);
    }

    public void clickTrashIcon() {
        trashIcon.click();
    }
}
