package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

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

        return Double.parseDouble(getPriceRangeValue(priceLabel).get(0)
                .replace("$", ""));
    }

    public Double getActualMaxPriceValue() {
        String priceLabel = getPriceLabel();
        getPriceRangeValue(priceLabel);

        return Double.parseDouble(getPriceRangeValue(priceLabel).get(1)
                .replace("$", ""));
    }

    public void moveMin(Double target) {
        while (!getActualMinPriceValue().equals(target)) {
            actions.clickAndHold(leftFilterHandle)
                    .moveByOffset(SINGLE_MOVE, 0)
                    .build().perform();
        }

        actions.release()
                .build().perform();

    }

    public void moveMax(Double target) {
        while (!getActualMaxPriceValue().equals(target)) {
            actions.clickAndHold(rightFilterHandle)
                    .moveByOffset(-SINGLE_MOVE, 0)
                    .build().perform();
        }

        actions.release()
                .build().perform();
    }

}

