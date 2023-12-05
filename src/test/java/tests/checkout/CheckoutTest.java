package tests.checkout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CategoryPage;
import pages.LoginPage;
import pages.ProductPage;
import pages.components.ProductMiniatureComponent;
import pages.components.cart.AddToCartPopupComponent;
import providers.UrlProvider;
import tests.BaseTest;

import java.time.Duration;

public class CheckoutTest extends BaseTest {
    CategoryPage categoryPage;
    LoginPage loginPage;
    ProductMiniatureComponent productMiniatureComponent;
    ProductPage productPage;
    AddToCartPopupComponent addToCartPopupComponent;

    @BeforeEach
    public void setUpLoginTest() {
        categoryPage = new CategoryPage(BaseTest.driver);
        loginPage = new LoginPage(driver);
        productMiniatureComponent = new ProductMiniatureComponent(driver);
        productPage = new ProductPage(driver);
        addToCartPopupComponent = new AddToCartPopupComponent(driver);
    }

    @Test
    void checkoutTest() {
        driver.get(UrlProvider.LOGIN);
        loginPage.login("ty @gmail.com", "adminADMIN");
        driver.get(UrlProvider.APP);
        productMiniatureComponent.openProductView("THE BEST IS YET POSTER");
        productPage.addToCart();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(By.id("blockcart-modal"), "class", "modal fade in"));
        addToCartPopupComponent.proceedToCheckout();



    }

}

