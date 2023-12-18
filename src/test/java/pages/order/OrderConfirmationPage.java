package pages.order;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;

public class OrderConfirmationPage extends BasePage {
    @FindBy(css = "#order-details li")
    private WebElement orderReferenceSymbol;
    @FindBy(css = ".total-value td:nth-of-type(2)")
    private WebElement orderTotalLabel;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String extractOrderReference() {
        String orderReference = orderReferenceSymbol.getText();
        return orderReference.replaceAll("Order reference: ", "");
    }
    public BigDecimal getOrderTotal(){
        return getPrice(orderTotalLabel);
    }

}
