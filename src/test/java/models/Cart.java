package models;

import java.util.List;

public class Cart {
    private List<CartLine> products;

    public void addProduct(CartLine product) {
        // sprawdz prod czy jest w liscie
        // jeśli nie
        products.add(product);
        // jeśli tak
        //  quantity.update (ta musi update qty i update totalPrice)

    }
}
