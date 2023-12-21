package pages.product;

import lombok.extern.slf4j.Slf4j;
import models.cart.CartLine;
import models.cart.CartLineQueryable;
import models.product.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.Random;

@Slf4j
public class ProductPage extends BasePage implements CartLineQueryable {
    @FindBy(css = ".qty #quantity_wanted")
    private WebElement quantityInput;
    @FindBy(css = ".btn.add-to-cart")
    private WebElement addToCartButton;
    @FindBy(css = ".h5.product-price span")
    private WebElement priceLabel;
    @FindBy(css = ".product-container [itemprop='name']")
    private WebElement productName;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public CartLine toCartLine() {
        return new CartLine(
                new Product(productName.getText(), getPrice(priceLabel)),
                Integer.parseInt(getCurrentQuantity())
        );
    }

    public ProductPage setQuantity(String quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(quantity);

        return this;
    }

    public String getCurrentQuantity() {
        return quantityInput.getAttribute("value");
    }

    public int generateRandomQuantity() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }

    public void addProduct() {
        addToCartButton.click();
    }

    public BigDecimal getProductPrice() {
        return getPrice(priceLabel);
    }
}


