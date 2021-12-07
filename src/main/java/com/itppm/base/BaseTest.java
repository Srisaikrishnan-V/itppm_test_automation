package com.itppm.base;

import org.testng.annotations.*;

public class BaseTest extends BaseMethods {

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        initializeDriver(getPropertyValue("browser"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
