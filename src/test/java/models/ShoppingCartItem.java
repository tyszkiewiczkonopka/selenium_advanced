package models;

import lombok.Getter;


@Getter
public class ShoppingCartItem {

    private String productName;
    private double price;
    private int quantity;

    public ShoppingCartItem(String productName, double price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalProductLineCost() {
        return price * quantity;
    }

    public void increaseProductQuantity(int quantityToAdd) {
        this.quantity += quantityToAdd;
    }


}


