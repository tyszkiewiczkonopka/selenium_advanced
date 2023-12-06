package pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class PlacedOrderDetailsPage extends BasePage {
    public PlacedOrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".hidden-sm-down.table.table-bordered.table-striped > tbody > tr > td:nth-of-type(1)")
    private WebElement orderDate;
    @FindBy(css = "#order-history .table > tbody > tr > td:nth-of-type(2) > span")
    private WebElement orderStatus;
    @FindBy(css = "tfoot .line-total td:last-child\n")
    private WebElement orderTotal;

    public String getOrderStatus() {
        return orderStatus.getText();
    }
    public String getOrderDate() {
        return orderDate.getText();
    }
    public double extractOrderTotal() {
        String orderTotalText = orderTotal.getText();
        return Double.parseDouble(orderTotalText.replaceAll("[^\\d.]", ""));
    }
}
