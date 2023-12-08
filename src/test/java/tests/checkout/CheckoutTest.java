package tests.checkout;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CategoryPage;
import pages.LoginPage;
import pages.ProductPage;
import pages.components.ProductMiniatureComponent;
import pages.components.cart.CartPopupComponent;
import providers.UrlProvider;
import tests.BaseTest;

import java.time.Duration;

public class CheckoutTest extends BaseTest {
    @Test
    void checkoutTest() {
        driver.get(UrlProvider.LOGIN);
        at(LoginPage.class).login("ty @gmail.com", "adminADMIN");
        driver.get(UrlProvider.APP);
        at(ProductMiniatureComponent.class).openProductView("THE BEST IS YET POSTER");
        at(ProductPage.class).addProduct();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(By.id("blockcart-modal"), "class", "modal fade in"));
        at(CartPopupComponent.class).proceedToCheckout();

        // do usunięcia?
    }

}

