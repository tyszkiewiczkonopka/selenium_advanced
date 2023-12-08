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

    public List<WebElement> getAllMiniatures() {
        return productMiniatures;
    }

    public void openProductView(String desiredProductName) {
        WebElement element = driver.findElement(By.linkText(desiredProductName));
        element.click();
    }

    // TODO: getProductName do BasePage?
    public String getProductNameFromMiniature(WebElement productMiniature) {
        return productTitle.getText();
    }
//    public String getProductName() {

//        return productTitle.getText();

//    }
//    public double extractProductPriceFromProductMiniature() {
//        String productPriceText = productPrice.getText();
//        productPriceText = productPriceText.replaceAll("\\.(?=.*\\.)", "");
//
//        try {
//            return Double.parseDouble(productPriceText);
//        } catch (NumberFormatException e) {
//            log.warn("Invalid product price format: {}", productPriceText);
//            throw e;
//        }

//    }
}
