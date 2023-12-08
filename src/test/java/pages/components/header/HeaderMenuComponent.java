package pages.components.header;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public class HeaderMenuComponent extends BasePage {
    @FindBy(css = "[data-depth=\"0\"] > .category")
    private List<WebElement> menuCategories;

    public HeaderMenuComponent(WebDriver driver) {
        super(driver);
    }

    public List<String> getAllCategoryIds() {
        return menuCategories.stream()
                .map(category -> category.getAttribute("id"))
                .toList();
    }

    public List<String> getSubcategoryIds(WebElement category) {
        return category
                .findElements(By.cssSelector(".category"))
                .stream()
                .map((subcategory) -> subcategory.getAttribute("id"))
                .toList();
    }


    public List<String> getAllCategoryNames() {
        List<String> categoryNames = new ArrayList<>();
        for (WebElement categoryName : menuCategories) {
            String categoryNameText = categoryName.getText().toUpperCase();
            categoryNames.add(categoryNameText);
        }
        return categoryNames;
    }

    public void chooseMenuCategory(String categoryName) { // type -> CategoryName?
        WebElement categoryElement = menuCategories.stream()
                .filter(element -> element.getText().equalsIgnoreCase(categoryName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Category not found: " + categoryName));
        categoryElement.click();
    }

    public void hoverOverMenuCategory(WebElement menuCategory) {
        actions.moveToElement(menuCategory).build().perform();
    }



}
