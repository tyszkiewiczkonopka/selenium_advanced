package pages.categories.filters;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

@Slf4j
public class FilterPriceRangeComponent extends BasePage {
    private final int SINGLE_MOVE = 25;
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
        return List.of(targetPriceRange.split(" - "));
    }

    private String getPriceLabel() {
        return priceElement.getText();
    }

    public Double getActualMinPriceValue() {
        String priceLabel = getPriceLabel();
        getPriceRangeValue(priceLabel);

        return Double.parseDouble(getPriceRangeValue(priceLabel).get(0).replace("$", ""));
    }

    public Double getActualMaxPriceValue() {
        String priceLabel = getPriceLabel();
        getPriceRangeValue(priceLabel);

        return Double.parseDouble(getPriceRangeValue(priceLabel).get(1).replace("$", ""));
    }

    public FilterPriceRangeComponent moveMin(Double target) {

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

    public FilterPriceRangeComponent moveMax(Double target) {
        while (!getActualMaxPriceValue().equals(target)) {
            actions.clickAndHold(rightFilterHandle)
                    .moveByOffset(-SINGLE_MOVE, 0)
                    .build().perform();
        }

        actions.release()
                .build().perform();
        return this;
    }

}

