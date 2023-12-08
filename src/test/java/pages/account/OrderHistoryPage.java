package pages.account;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

@Slf4j
public class OrderHistoryPage extends BasePage {
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

//    @FindBy(css = ".table")
//    private WebElement ordersTable;
    @FindBy(css = ".table tbody tr")
    private List<WebElement> orderTableRows;
    @FindBy(css = "[data-link-action='view-order-details']")
    private WebElement detailsLink;

    public void openOrderDetails(String orderReference) { // type: what page after opening order details -> PlacedOrderDetailsPage?
        WebElement foundRow = null;
        for (WebElement row : orderTableRows) {
            if (row.getText().contains(orderReference)) {
                foundRow = row;
                break;
            }
        }
        if (foundRow != null) {
            detailsLink.click();
            log.info("Order details opened for order: " + orderReference);
        } else {
            log.info("No row found for order: " + orderReference);
        }
    }


}