package tests.cart;

import lombok.extern.slf4j.Slf4j;
import models.Cart;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ProductPage;
import providers.UrlProvider;
import tests.BaseTest;

@Slf4j
public class GenericRandomProductsTest extends BaseTest {
    @Test
    public void genericCart() {
        driver.get(UrlProvider.APP);
        String randomProductName = at(HomePage.class).getRandomProductName();
        //  int randomQuantity = productPage.getRandomQuantity();
        //  productMiniatureComponent.openProductView(homePageRandomProductName);
        //  cartLine = productPage.addProduct();
        //  cartPage.getAllShoppingCartItems();
//        Cart cart = new Cart(driver);
//
//        cart.addToCartRandomProduct(homePageRandomProductName);
//        cartPage.getAllShoppingCartItems();
        Cart cart = new Cart();
        cart.addCartLine(at(ProductPage.class).toCartLine());
        at(ProductPage.class).addProduct();

    }


}
