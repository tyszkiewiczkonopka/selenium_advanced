package pages.categories.filters;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class FilterPriceRangeComponent extends BasePage {
    private final int SINGLE_MOVE = 25;
    private final String DOLLAR_SYMBOL = "$";
    private final String HYPHEN_SYMBOL = " - ";

    @FindBy(css = "[id^='slider-range_'] a:nth-of-type(1)")
    private WebElement leftFilterHandle;
    @FindBy(css = "[id^='slider-range_'] a:nth-of-type(2)")
    private WebElement rightFilterHandle;
    @FindBy(css = "[id^='facet_label_']")
    private WebElement priceElement;

    public FilterPriceRangeComponent(WebDriver driver) {
        super(driver);
    }

    private List<String> getPriceRangeValue(String targetPriceRange) {
        return List.of(targetPriceRange.split(HYPHEN_SYMBOL));
    }

    private String getPriceLabel() {
        return priceElement.getText();
    }

    public BigDecimal getActualMinPriceValue() {
        String priceLabel = getPriceLabel();
        getPriceRangeValue(priceLabel);

        return new BigDecimal(getPriceRangeValue(priceLabel).get(0).replace(DOLLAR_SYMBOL, ""));
    }

    public BigDecimal getActualMaxPriceValue() {
        String priceLabel = getPriceLabel();
        getPriceRangeValue(priceLabel);

        return new BigDecimal(getPriceRangeValue(priceLabel).get(1).replace(DOLLAR_SYMBOL, ""));
    }

    public FilterPriceRangeComponent moveMin(BigDecimal target) {

        int maxAttempts = 10;
        int attempts = 0;

        while (!getActualMinPriceValue().equals(target) && attempts < maxAttempts) {
            try {
                actions.clickAndHold(leftFilterHandle)
                        .moveByOffset(SINGLE_MOVE, 0)
                        .build().perform();
                attempts++;
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                log.error("Exception occurred: " + e.getMessage());
                break;
            }
        }
        actions.release()
                .build().perform();
        return this;
    }

    public FilterPriceRangeComponent moveMax(BigDecimal target) {
        int maxAttempts = 10;
        int attempts = 0;

        while (!getActualMaxPriceValue().equals(target) && attempts < maxAttempts) {
            try {
                actions.clickAndHold(rightFilterHandle)
                        .moveByOffset(-SINGLE_MOVE, 0)
                        .build().perform();
                attempts++;
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                log.error("Exception occurred: " + e.getMessage());
                break;
            }
        }

        actions.release()
                .build().perform();
        return this;
    }

}

