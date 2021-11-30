package com.itppm.pages;

import com.itppm.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BaseTest {

    @FindBy(id = "nav-link-accountList")
    private WebElement eleHelloSignIn;
    @FindBy(xpath = "(//*[text()='Sign in'])[1]")
    private WebElement eleSignIn;
    @FindBy(id = "twotabsearchtextbox")
    private WebElement eleSearchBar;
    @FindBy(xpath = "//*[@type='submit']")
    private WebElement eleSearchIcon;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public HomePage mouseOverToAccountsList() {
        mouseOver(eleHelloSignIn);
        return new HomePage();
    }


    public HomePage typeSearchBox(String input) {
        type(eleSearchBar, input);
        return this;
    }

    public HomePage clickSearch() {
        click(eleSearchIcon);
        return this;
    }

    public LoginPage clickHomePageSignIn() {
        click(eleSignIn);
        return new LoginPage();
    }
}
