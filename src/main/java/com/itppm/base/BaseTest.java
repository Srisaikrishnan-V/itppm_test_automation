package com.itppm.base;

import org.testng.annotations.*;

public class BaseTest extends BaseMethods {

    @BeforeMethod
    public void setUp() throws Exception {
        initializeDriver(getPropertyValue("browser"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
