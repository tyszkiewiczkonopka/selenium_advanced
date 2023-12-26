package pages.cart;

import lombok.extern.slf4j.Slf4j;
import models.cart.CartLine;
import models.cart.CartLineQueryable;
import models.product.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

@Slf4j
public class CartLineComponent extends BasePage implements CartLineQueryable {
    @FindBy(css = ".product-line-info")
    private WebElement productNameLabel;
    @FindBy(css = ".product-price .current-price")
    private WebElement productPriceLabel;
    @FindBy(css = ".js-cart-line-product-quantity")
    private WebElement quantityInput;

    public CartLineComponent(WebDriver driver, WebElement lineElement) {
        super(driver, lineElement);
    }

    @Override
    public CartLine toCartLine() {
        return new CartLine(
                new Product(productNameLabel.getText(), getPrice(productPriceLabel)),
                getInputValueAsInt(quantityInput)
        );
    }

}
