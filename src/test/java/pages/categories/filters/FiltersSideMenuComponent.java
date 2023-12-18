package pages.categories.filters;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;


public class FiltersSideMenuComponent extends BasePage {

    @FindBy(id = "search_filters")
    private WebElement filtersMenu;
    @FindBy(css = ".btn-tertiary.js-search-filters-clear-all")
    private WebElement clearAllButton;

    public FiltersSideMenuComponent(WebDriver driver) {
        super(driver);
    }

    public WebElement getFiltersMenu() {
        return filtersMenu;
    }

    public FiltersSideMenuComponent clearAllFilters() {
        clearAllButton.click();
        return this;
    }


}

