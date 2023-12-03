package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class FiltersSideMenuComponent extends BasePage {
    @FindBy(id = "search_filters")
    private WebElement filtersMenu;

    @FindBy(css = ".btn-tertiary.js-search-filters-clear-all")
    private WebElement clearAllButton;

    public FiltersSideMenuComponent(WebDriver driver) {
        super(driver);
    }

    public boolean isFiltersMenuDisplayed() {
        return filtersMenu.isDisplayed();
    }

    public void clearAllFilters() {
        clearAllButton.click();
    }


}

