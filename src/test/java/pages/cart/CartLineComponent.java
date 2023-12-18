package pages.cart;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import models.cart.CartLine;
import models.cart.CartLineQuerable;
import models.product.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

import java.util.List;

@Slf4j
public class CartLineComponent extends BasePage implements CartLineQuerable {
    @FindBy(css = ".product-line-info")
    private WebElement productNameLabel;
    @FindBy(css = ".product-price .current-price")
    private WebElement productPriceLabel;
    @FindBy(css = ".js-cart-line-product-quantity")
    private WebElement quantityInput;
    @FindBy(css = ".col-md-6.col-xs-2.price .product-price")
    private WebElement totalProductPriceLabel;


    public CartLineComponent(WebDriver driver, WebElement lineElement) {
        super(driver, lineElement);
    }

    @Override
    public CartLine toCartLine() {
        return new CartLine(
                new Product(productNameLabel.getText(), getPrice(productPriceLabel)),
                Integer.parseInt(quantityInput.getAttribute("value"))
        );
    }

    // TODO: CHODZI O TAKĄ ZMIANĘ?
    public int getInputValueAsInt(WebElement inputElement){
        return Integer.parseInt(inputElement.getAttribute("value"));
    }


}
