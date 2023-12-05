package pages.components;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

@Getter
@Slf4j
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
    @FindBy(css = ".product")
    private List<WebElement> productMiniatures;
    public List<WebElement> getAllMiniatures(){
        return productMiniatures;
    }

    public String getProductName() {
        return productTitle.getText();
    }

    public void openProductView(String desiredProductName) {
        WebElement element = driver.findElement(By.linkText(desiredProductName));
        element.click();
    }
//    public double extractProductPrice(String desiredProductName){
//        WebElement product = categoryPage.findProductByName(desiredProductName);
//        if (product != null) {
//            String productPriceText = productPrice.getText();
//            return Double.parseDouble(productPriceText.replaceAll("[^\\d.]", ""));
//        } else {
//            log.error("Product not found with name: " + desiredProductName);
//            return 0.0;
//        }
//    }

    public double extractProductPriceFromProductMiniature() {
        String productPriceText = productPrice.getText();
        productPriceText = productPriceText.replaceAll("\\.(?=.*\\.)", "");

        try {
            return Double.parseDouble(productPriceText);
        } catch (NumberFormatException e) {
            log.warn("Invalid product price format: {}", productPriceText);
            throw e;
        }    }

    public String getProductNameFromMiniature(WebElement productMiniature) {
        return productTitle.getText();
    }
}
