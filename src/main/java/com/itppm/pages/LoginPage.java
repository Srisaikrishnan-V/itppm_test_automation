package com.itppm.pages;

import com.itppm.base.BaseMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BaseMethods {

    @FindBy(id = "ap_email")
    private WebElement eleEmail;
    @FindBy(id = "continue")
    private WebElement eleContinue;
    @FindBy(id = "ap_password")
    private WebElement elePassword;
    @FindBy(id = "signInSubmit")
    private WebElement eleSignIn;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public LoginPage enterEmail(String email) {
        type(eleEmail, email);
        return this;
    }

    public LoginPage clickContinue() {
        click(eleContinue);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(elePassword, password);
        return this;
    }

    public LandingPage clickSignIn() {
        click(eleSignIn);
        return new LandingPage();
    }
}
