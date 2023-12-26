package pages.common.header;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public List<String> getSubcategoryIds(String categoryId) {
        WebElement category = driver.findElement(By.id(categoryId));

        log.info(">>>>> Category: " + category.getText());
        hoverOverMenuCategory(category);

        return category
                .findElements(By.cssSelector(".category"))
                .stream()
                .map((subcategory) -> subcategory.getAttribute("id"))
                .toList();
    }

    public String getSubcategoryNameAndOpen(String categoryId, String subcategoryId) {
        WebElement category = driver.findElement(By.id(categoryId));

        log.info(">>>>> Category (subcategories): " + category.getText());
        hoverOverMenuCategory(category);

        WebElement subcategory = driver.findElement(By.id(subcategoryId));
        String subcategoryName = subcategory.getText();
        subcategory.click();

        return subcategoryName;
    }


    public List<String> getAllCategoryNames() {
        return menuCategories.stream()
                .map(categoryName -> categoryName.getText().toUpperCase())
                .collect(Collectors.toList());
    }

    public void chooseMenuCategory(String categoryName) {
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
