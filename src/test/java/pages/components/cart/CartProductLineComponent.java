package pages.components.cart;

import lombok.Getter;


@Getter
public class CartProductLineComponent {
    private String productName;
    private double price;
    private int quantity;



    public double getTotalProductLineCost() {
        return price * quantity;
    }




}


