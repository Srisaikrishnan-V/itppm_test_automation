package com.itppm.testcases;

import com.itppm.base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.itppm.pages.HomePage;

public class TC002_VerifyPageTitle extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void setData(){
        dataSheetName = "TC002";
    }

    @Description("Verifying the boat Airdopes Product title with incorrect title")
    @Test(groups = {"regression", "smoke"},dataProvider = "fetchData")
    public void verifyPageTitle(String expectedTitle) throws Exception {
        String title = getTitle();
        Assert.assertEquals(title, expectedTitle);

    }


}
