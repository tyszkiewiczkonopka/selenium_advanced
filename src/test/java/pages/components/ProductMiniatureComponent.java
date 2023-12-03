package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class ProductMiniatureComponent extends BasePage {
    public ProductMiniatureComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-price-and-shipping")
    private WebElement productPrice;

    public WebElement getProductPrice(){
        return productPrice;
    }


}
