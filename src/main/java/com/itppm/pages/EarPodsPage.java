package com.itppm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.itppm.base.BaseMethods;

public class EarPodsPage extends BaseMethods {

    @FindBy(xpath = "(//*[text()[contains(.,'₹1,000 - ₹5,000')]])[1]")
    private WebElement eleFilter1Kto5K;
    @FindBy(xpath = "//*[text()='boAt Airdopes 441 Bluetooth Truly Wireless in Ear Earbuds with Mic (Raging Red)']")
    private WebElement eleFBoatEarpod;

    public EarPodsPage() {
        PageFactory.initElements(driver, this);
    }

    public EarPodsPage click1000To5000() {
        click(eleFilter1Kto5K);
        return this;
    }

    public EarPodsPage verifyTitle() {
        verifyPartialTitle("earpods");
        return this;
    }

    public BoatEarpodDetailsScreen selectBoatEarPods() {
        click(eleFBoatEarpod);
        return new BoatEarpodDetailsScreen();

    }
}
