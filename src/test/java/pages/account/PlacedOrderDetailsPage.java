package pages.account;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;

public class PlacedOrderDetailsPage extends BasePage {
    @FindBy(css = ".hidden-sm-down.table.table-bordered.table-striped > tbody > tr > td:nth-of-type(1)")
    private WebElement orderDateLabel;
    @FindBy(css = "#order-history .table > tbody > tr > td:nth-of-type(2) > span")
    private WebElement orderStatusLabel;
    @FindBy(xpath = "./html//table[@id='order-products']/tfoot/tr[3]/td[2]")
    private WebElement orderTotalLabel;

    public PlacedOrderDetailsPage(WebDriver driver) {
        super(driver);
    }


    public String getOrderDate() {
        return getText(orderDateLabel);
    }

    public BigDecimal getOrderTotal() {
        return getPrice(orderTotalLabel);
    }

    public String getOrderStatus() {
        return getText(orderStatusLabel);
    }
}
