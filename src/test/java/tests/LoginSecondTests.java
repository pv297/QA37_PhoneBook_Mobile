package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

public class LoginSecondTests  extends AppiumConfig {
@Test
    public void loginSuccess(){
        new AuthenticationScreen(driver)
                .fillEmail("marym@gmail.com")
                .fillPassword("Mm12345@")
                .submitLogin()
                .isAccountOpened()
                .logout();

    }
    @Test
    public void loginSuccessModel(){
    new AuthenticationScreen(driver)
            .fillLoginRegistrationForm(Auth.builder().email("marym@gmail.com").password("Mm12345@").build())
            .submitLogin()
            .isAccountOpened()
            .logout();
    }

    @Test
    public void loginWrongEmail(){
        new AuthenticationScreen(driver).fillLoginRegistrationForm(Auth.builder()
                        .email("marymgmail.com").password("Mm12345@").build())
                .submitLoginPasswordNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }
}
