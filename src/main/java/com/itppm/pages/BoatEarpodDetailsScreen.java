package com.itppm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.itppm.base.BaseMethods;

public class BoatEarpodDetailsScreen extends BaseMethods {

    @FindBy(id = "inline-twister-expanded-dimension-text-color_name")
    private WebElement eleColour;
    @FindBy(xpath = "//*[@id='productTitle']")
    private WebElement eleModel;
    @FindBy(xpath = "//*[text()[contains(.,'Account & Lists')]]")
    private WebElement eleAccount;
    @FindBy(xpath = "//*[text()='Sign Out']")
    private WebElement eleSignOut;

    public BoatEarpodDetailsScreen() {
        PageFactory.initElements(driver, this);
    }

    public BoatEarpodDetailsScreen verifyPartialTitle() {
        boolean title = verifyPartialTitle("llboAt");
        return this;
    }

    public BoatEarpodDetailsScreen verifyEarbudColour(String colour) {
        verifyExactText(eleColour, colour);
        return this;
    }

    public BoatEarpodDetailsScreen verifyItemModel(String model) {
        verifyPartialText(eleModel, model);
        return this;

    }

    public BoatEarpodDetailsScreen mouseOverOnAccounts() {
        mouseOver(eleAccount);
        return this;
    }

    public LoginPage clickSignOut() {
        click(eleSignOut);
        return new LoginPage();
    }


}
