package models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

@Slf4j
@Getter
@Setter
public class Cart {
    private List<CartLine> products;

    public void addCartLine(CartLine cartLineToAdd) {
        for (CartLine cartLine : products) {
            if (cartLine.getProduct().getName().equals(cartLineToAdd.getProduct().getName())) {
                cartLine.increaseQuantity(cartLineToAdd.getQuantity());
                return;
            }
        }
        products.add(cartLineToAdd);
    }


//
//
//    public void addToCartRandomProduct(String randomProductName) {
//        productMiniatureComponent = new ProductMiniatureComponent(driver);
//
//        productMiniatureComponent.openProductView(randomProductName);
//        String randomQuantity = String.valueOf(productPage.getRandomQuantity());
//        productPage.setQuantity(randomQuantity);
//        productPage.addProduct();
//        log.info("Added to cart: " + randomProductName + " x " + randomQuantity);
//    }
//    public Cart getAllShoppingCartItems() {
//        log.info("All products in the cart: " + shoppingCartItems.toString());
//        Cart cart = new Cart(driver);
//        cart.setProducts();
//        return Cart;

//    }
//    public void addToCartMultipleRandomProducts(int numberOfProducts) {
//        for (int i = 0; i < numberOfProducts; i++) {
//            addToCartRandomProduct();
//            if (i == numberOfProducts - 1) {
//                addToCartPopupComponent.proceedToCheckout();
//            } else {
//                driver.navigate().back();
//            }
//        }

//    }
//    public void assertProductAddedOrQuantityUpdated(String productName, int addToCartQuantity){
//        if(isProductInCart(productName)){
//            String currentQuantityInCart = shoppingCartPage.getCurrentQuantityInCart(productName);
//            String increasedQuantityInCart = currentQuantityInCart + addToCartQuantity;
//            shoppingCartPage.updateQuantityInCart(Integer.parseInt(increasedQuantityInCart));
//        } else {
//
//        }
//    }
}
