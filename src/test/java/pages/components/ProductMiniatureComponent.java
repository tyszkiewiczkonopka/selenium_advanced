package pages.components;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

@Getter
public class ProductMiniatureComponent extends BasePage {
    public ProductMiniatureComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-price-and-shipping")
    private WebElement productPrice;
    @FindBy(css = ".h3.product-title a")
    private WebElement productTitle;
    @FindBy(css = "article.product-miniature .quick-view")
    private WebElement productQuickView;
    @FindBy(css = "article.product-miniature img")
    private WebElement productImage;

    public String getProductName(){
        return productTitle.getText();
    }

    public void openProductView(String desiredProductName){
        WebElement element = driver.findElement(By.linkText(desiredProductName));
        element.click();
    }
    public String getProductPrice(){
        return productPrice.getText();
    }



}
