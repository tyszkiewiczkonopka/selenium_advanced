package pages;

import org.openqa.selenium.WebDriver;
import models.ShoppingCartItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage{
    private List<ShoppingCartItem> shoppingCartItems;


    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        this.shoppingCartItems = new ArrayList<>();

    }

    public void addToCart(String productName, double price, int quantityToAdd) {
        for (ShoppingCartItem item : shoppingCartItems) {
            if (item.getProductName().equals(productName)) {
                item.increaseProductQuantity(quantityToAdd);
                return;
            }
        }

        ShoppingCartItem newItem = new ShoppingCartItem(productName, price, quantityToAdd);
        shoppingCartItems.add(newItem);
    }


}
