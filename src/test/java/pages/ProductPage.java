package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import models.CartLine;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.ProductMiniatureComponent;

import java.math.BigDecimal;
import java.util.Random;

@Slf4j
@Getter
public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".qty #quantity_wanted")
    private WebElement quantityInput;
    @FindBy(css = ".btn.add-to-cart")
    private WebElement addToCartButton;
    @FindBy(css = ".h5.product-price span")
    private WebElement priceInput;
    @FindBy(css = ".product-container [itemprop='name']")
    private WebElement productName;


    public CartLine toCartLine() {
        return new CartLine(
                new Product(productName.getText(), getPrice(priceInput)),
                Integer.parseInt(getCurrentQuantity())
        );
    }
    public void setQuantity(String quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
    }
    public String getCurrentQuantity() {
        return quantityInput.getAttribute("value");
    }

    public void addProduct() {
        addToCartButton.click();
    }
}


