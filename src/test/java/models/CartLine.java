package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class CartLine {
    private Product product;
    private BigDecimal totalPrice;
    private int quantity;

    public CartLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getPrice().multiply(new BigDecimal(quantity));
    }

    public void increaseQuantity(int quantityToAdd) {
        quantity += quantityToAdd;
        totalPrice = product.getPrice().multiply(new BigDecimal(quantity));
    }

}
