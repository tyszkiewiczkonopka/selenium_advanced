package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmationPage extends BasePage{
    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "#order-details li")
    private WebElement orderReferenceSymbol;
    @FindBy(css = ".total-value td:nth-of-type(2)")
    private WebElement orderTotal;

    public String extractOrderReference(){
        String orderReference = orderReferenceSymbol.getText();
        return orderReference.replaceAll("Order reference: ", "");
    }
    public double extractOrderTotal() {
        String orderTotalText = orderTotal.getText();
        return Double.parseDouble(orderTotalText.replaceAll("[^\\d.]", ""));
    }
}
