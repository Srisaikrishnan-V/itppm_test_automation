package com.itppm.testcases;


import com.itppm.base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.itppm.pages.HomePage;

public class TC001_VerifyEarPodProduct extends BaseTest {

    @BeforeTest(alwaysRun = true)
    public void setData(){
         dataSheetName = "TC003_ExcelTest";
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
        Thread.sleep(5000);
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
        Thread.sleep(5000);
        Assert.assertEquals(title, "Amazon.in : boAt Airdopes 441 Bluetooth Truly Wireless in Ear Earbuds with Mic (Raging Red)");
    }

    @Test(groups = {"smoke", "regression"}, dataProvider = "fetchData")
    public void verifyEarPodsSmokeRegr(String input) throws Exception {
        String title = new HomePage()
                .mouseOverToAccountsList()
                .clickHomePageSignIn()
                .enterEmail(getPropertyValue("username"))
                .clickContinue()
                .enterPassword(getPropertyValue("password"))
                .clickSignIn()
                .typeSearchBox(input)
                .clickSearch()
                .selectBoatEarPods()
                .getTitle();
        Thread.sleep(5000);
        Assert.assertEquals(title, "Amazon.in : boAt Airdopes 441 Pro Bluetooth Truly Wireless in Ear Earbuds with Mic (Raging Red)");
    }
}
