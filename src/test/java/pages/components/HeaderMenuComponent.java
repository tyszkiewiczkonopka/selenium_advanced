package pages.components;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public class HeaderMenuComponent extends BasePage {

    public HeaderMenuComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "top-menu")
    private WebElement topMenu;
    @FindBy(css = ".category .dropdown-item:not(.dropdown-submenu)")
    private List<WebElement> menuCategories;
    @FindBy(css = ".category .dropdown-submenu")
    private List<WebElement> menuSubcategories;


    public List<WebElement> getAllCategoryElements() {
        return menuCategories;
    }

    public List<WebElement> getSubcategoryElements() {
        return menuSubcategories;
    }

    public List<String> getAllCategoryNames() {
        List<String> categoryNames = new ArrayList<>();
        for (WebElement categoryName : menuCategories) {
            String categoryNameText = categoryName.getText().toUpperCase();
            categoryNames.add(categoryNameText);
        }
        return categoryNames;
    }


    public void chooseMenuCategory(String category) {
        WebElement categoryElement = menuCategories.stream()
                .filter(element -> element.getText().equalsIgnoreCase(category))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Category not found: " + category));
        categoryElement.click();
    }



    public List<String> getAllSubcategoryNames() {
        List<String> subcategoryNames = new ArrayList<>();
        for (WebElement subcategoryName : menuSubcategories) {
            String subcategoryNameText = subcategoryName.getText().toUpperCase();
            subcategoryNames.add(subcategoryNameText);
        }
        return subcategoryNames;
    }

    public void hoverOverMenuCategory(WebElement menuCategory) {
        actions.moveToElement(menuCategory).perform();
    }

    public void hoverOverSubmenuCategory(WebElement menuCategory, WebElement subcategory) {
        hoverOverMenuCategory(menuCategory);
        actions.moveToElement(subcategory).perform();
    }

    public void chooseSubcategory(WebElement menuCategory, WebElement subcategory) {
        hoverOverMenuCategory(menuCategory);
        actions.moveToElement(subcategory).perform();
        subcategory.click();
    }


}
