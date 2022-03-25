package com.itppm.base;

import com.itppm.pages.HomePage;
import com.itppm.utils.DataInputProvider;
import org.testng.annotations.*;

import java.util.Set;

public class BaseTest extends BaseMethods {

    public String dataSheetName;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        initializeDriver(getPropertyValue("browser"));
        loginApp();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        logoutApp();
        driver.quit();
    }

    @DataProvider(name = "fetchData")
    public Object[][] getData() {
        return DataInputProvider.getSheet(dataSheetName);
    }

    public void loginApp() throws Exception {
        new HomePage()
                .mouseOverToAccountsList()
                .clickHomePageSignIn()
                .enterEmail(getPropertyValue("username"))
                .clickContinue()
                .enterPassword(getPropertyValue("password"))
                .clickSignIn();
    }

    public void logoutApp() {
        new HomePage()
                .mouseOverToAccountsList()
                .clickHomePageSignOut();
    }

    @AfterMethod(alwaysRun = true)
    public void navigateToLandingpage() {
        Set<String> windowHandles = driver.getWindowHandles();
        if (windowHandles.size() > 1) {
            switchToWindow(1);
            closeCurrentWindow();
            switchToWindow(0);
        }
    }
}
