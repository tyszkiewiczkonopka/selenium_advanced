package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.ProductMiniatureComponent;

import java.util.List;
import java.util.Random;

@Slf4j
public class ProductPage extends BasePage {
    ProductMiniatureComponent productMiniatureComponent;
    ShoppingCartPage shoppingCartPage;

    public ProductPage(WebDriver driver) {
        super(driver);
        productMiniatureComponent = new ProductMiniatureComponent(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @FindBy(css = ".qty #quantity_wanted")
    private WebElement quantityInput;
    @FindBy(css = ".btn.add-to-cart")
    private WebElement addToCartButton;
    @FindBy(css = ".h5.product-price span")
    private WebElement price;
    @FindBy(css = ".product-container [itemprop='name']")
    private WebElement productName;

    public void setQuantity(String quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
    }

    public String getCurrentQuantity() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].value", quantityInput);
    }

    public double extractProductPriceFromProductPage() {
        String productPriceText = price.getText();
        return Double.parseDouble(productPriceText.replaceAll("[^\\d.]", ""));
    }
    public void clickAddToCart(){
        addToCartButton.click();
    }



    public int getRandomQuantity() {
        return new Random().nextInt(10) + 1;
    }
}


