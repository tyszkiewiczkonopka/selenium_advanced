package pages.components.cart;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import models.CartLine;
import models.CartLineQuerable;
import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

import java.util.List;

@Slf4j
@Getter
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
        super(driver);
        PageFactory.initElements(new SearchContext() {
            @Override
            public List<WebElement> findElements(By by) {
                return lineElement.findElements(by);
            }

            @Override
            public WebElement findElement(By by) {
                return lineElement.findElement(by);
            }
        }, this);

    }

    @Override
    public CartLine toCartLine() {
        return new CartLine(
                new Product(productNameLabel.getText(), getPrice(productPriceLabel)),
                Integer.parseInt(quantityInput.getAttribute("value"))
        );
    }


}
