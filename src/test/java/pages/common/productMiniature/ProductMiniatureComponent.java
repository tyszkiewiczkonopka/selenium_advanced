package pages.common.productMiniature;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.product.ProductPage;

import java.math.BigDecimal;

@Slf4j
public class ProductMiniatureComponent extends BasePage {
    @FindBy(css = ".product-price-and-shipping")
    private WebElement productPriceLabel;

    public ProductMiniatureComponent(WebDriver driver) {
        super(driver);
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
