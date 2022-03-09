package com.itppm.base;

import com.itppm.utils.DataInputProvider;
import org.testng.annotations.*;

public class BaseTest extends BaseMethods {

    public String dataSheetName;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        initializeDriver(getPropertyValue("browser"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name="fetchData")
    public  Object[][] getData(){
        return DataInputProvider.getSheet(dataSheetName);
    }
}
