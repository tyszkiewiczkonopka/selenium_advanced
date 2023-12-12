package models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
public class Cart {
    private List<CartLine> products = new ArrayList<>();

    public void addCartLine(CartLine cartLineToAdd) {
        for (CartLine cartLine : products) {
            if (cartLine.getProduct().getName().equals(cartLineToAdd.getProduct().getName())) {
                cartLine.increaseQuantity(cartLineToAdd.getQuantity());
                return;
            }
        }
        products.add(cartLineToAdd);
    }

    public List<CartLine> getAllCartLines() {
        return products;
    }

    public BigDecimal calculateTotal(List<CartLine> products) {
        BigDecimal total = BigDecimal.ZERO;
        for (CartLine cartLine : products) {
            total = total.add(cartLine.getTotalPrice());
        }
        return total;
    }

    public void removeFirstCartLine() {
        if (!products.isEmpty()) {
            products.remove(0);
        }
    }


}
