package com.itppm.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.itppm.base.BaseMethods;

public class LandingPage extends BaseMethods {

    @FindBy(id = "twotabsearchtextbox")
    private WebElement eleSearchBar;
    @FindBy(xpath = "//*[@type='submit']")
    private WebElement eleSearchIcon;

    public LandingPage() {
        PageFactory.initElements(driver, this);
    }

    public LandingPage typeSearchBox(String input) {
        type(eleSearchBar, input);
        return this;
    }

    public EarPodsPage clickSearch() {
        click(eleSearchIcon);
        return new EarPodsPage();
    }

    public String getPageTitle() {
        return getTitle();
    }

    ;

}
