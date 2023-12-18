package pages.account;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

@Slf4j
public class OrdersHistoryPage extends BasePage {

    @FindBy(css = ".table tbody tr")
    private List<WebElement> orderTableRows;
    @FindBy(css = "[data-link-action='view-order-details']")
    private WebElement detailsLink;

    public OrdersHistoryPage(WebDriver driver) {
        super(driver);
    }

    public PlacedOrderDetailsPage openOrderDetails(String orderReference) {
        for (WebElement row : orderTableRows) {
            if (row.getText().contains(orderReference)) {
                detailsLink.click();
                log.info("Order details opened for order: " + orderReference);
                return new PlacedOrderDetailsPage(driver);
            }
        }
        log.info("No row found for order: " + orderReference);
        return new PlacedOrderDetailsPage(driver);
    }

}