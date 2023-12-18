package pages.common.productMiniature;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.product.ProductPage;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class ProductMiniatureComponent extends BasePage {
    @FindBy(css = ".product-price-and-shipping")
    private WebElement productPriceLabel;
    @FindBy(css = ".h3.product-title a")
    private WebElement productTitle;
    @FindBy(css = "article.product-miniature .quick-view")
    private WebElement productQuickView;
    @FindBy(css = "article.product-miniature img")
    private WebElement productImage;
    @FindBy(css = ".product")
    private List<WebElement> productMiniatures;
    public ProductMiniatureComponent(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getAllMiniatures() {
        return productMiniatures;
    }

    public ProductPage openProductView(String desiredProductName) {
        WebElement element = driver.findElement(By.linkText(desiredProductName));
        element.click();
        return new ProductPage(driver);
    }


    public BigDecimal getProductPrice() {
        return getPrice(productPriceLabel);
    }
}
