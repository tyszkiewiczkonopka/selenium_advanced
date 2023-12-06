package tests;

import lombok.extern.slf4j.Slf4j;
import models.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.SignInPage;
import providers.UrlProvider;

@Slf4j
public class LoginTest extends BaseTest {
    static UserFactory userFactory;
    SignInPage signInPage;

    @BeforeEach
    public void setUpLoginTest() {
        signInPage = new SignInPage(BaseTest.driver);
    }

    @Test
    void test() {
        userFactory = new UserFactory();
        BaseTest.driver.get(UrlProvider.SIGN_IN);
        signInPage.signInAsRegisteredUser();
    }

}
