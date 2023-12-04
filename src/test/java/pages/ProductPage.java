package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".qty #quantity_wanted")
    private WebElement quantityInput;
    @FindBy(css = ".btn.add-to-cart")
    private WebElement addToCartButton;

    public void changeQuantity(String quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
    }

    public String getCurrentQuantity() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].value", quantityInput);
    }

    public void addToCart() {
        addToCartButton.click();

//        List<WebElement> shoppingCartItems;
//        for (CartProductLineComponent item : shoppingCartItems) {
//            if (item.getProductName().equals(item)) {
//                item.increaseProductQuantity(getCurrentQuantity());
//                return;
//            }
//        }
//
//        CartProductLineComponent newItem = new CartProductLineComponent(productName, price, quantityToAdd);
//        shoppingCartItems.add(newItem);
    }
}


