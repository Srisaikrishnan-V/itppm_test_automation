package com.itppm.testcases;


import com.google.common.util.concurrent.Uninterruptibles;
import com.itppm.base.BaseTest;
import com.itppm.pages.LandingPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.itppm.pages.HomePage;

import java.util.concurrent.TimeUnit;

public class TC001_VerifyEarPodProduct extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void setData(){
         dataSheetName = "TC001";
    }

    @Description("Verifying the boat Airdopes Product title - Regression")
    @Test(groups = {"regression"})
    public void verifyEarPodsRegr() throws Exception {
        String title = new HomePage()
                .mouseOverToAccountsList()
                .clickHomePageSignIn()
                .enterEmail(getPropertyValue("username"))
                .clickContinue()
                .enterPassword(getPropertyValue("password"))
                .clickSignIn()
                .typeSearchBox("boAt Airdopes 441 Bluetooth Truly Wireless in Ear Earbuds with Mic (Raging Red)")
                .clickSearch()
                .selectBoatEarPods()
                .getTitle();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        System.out.println(title);
        Assert.assertEquals(title, "Amazon.in : boAt Airdopes 441 Bluetooth Truly Wireless in Ear Earbuds with Mic (Raging Red)");
    }

    @Test(groups = {"smoke"})
    public void verifyEarPodsSmoke() throws Exception {
        String title = new HomePage()
                .mouseOverToAccountsList()
                .clickHomePageSignIn()
                .enterEmail(getPropertyValue("username"))
                .clickContinue()
                .enterPassword(getPropertyValue("password"))
                .clickSignIn()
                .typeSearchBox("boAt Airdopes 441 Bluetooth Truly Wireless in Ear Earbuds with Mic (Raging Red)")
                .clickSearch()
                .selectBoatEarPods()
                .getTitle();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        Assert.assertEquals(title, "Amazon.in : boAt Airdopes 441 Bluetooth Truly Wireless in Ear Earbuds with Mic (Raging Red)");
    }

    @Test(groups = {"smoke", "regression"}, dataProvider = "fetchData")
    public void verifyEarPodsSmokeRegr(String input, String expectedTitle) throws Exception {
        String title = new LandingPage()
                .typeSearchBox(input)
                .clickSearch().getTitle();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        Assert.assertEquals(title, expectedTitle);
    }
}
