package com.itppm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.itppm.constants.FrameworkContants;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseMethods {
    public static WebDriver driver;
    public static Logger log = LogManager.getLogger(BaseMethods.class.getName());


    public WebDriver initializeDriver(String browser) throws IOException {
        try {
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", FrameworkContants.getChromedriverpath());
                ChromeOptions options = new ChromeOptions();
                if (browser.equalsIgnoreCase("headless")) {
                    options.addArguments("headless");
                }
                driver = new ChromeDriver(options);
                log.info("Chrome browser is successfully Launched");
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
                log.info("Firefox browser is successfully Launched");
            } else if (browser.equalsIgnoreCase("IE")) {
                driver = new InternetExplorerDriver();
                log.info("IE browser is successfully Launched");
            }
            driver.get(getPropertyValue("url"));
            driver.manage().window().maximize();
        } catch (WebDriverException e) {
            System.err.println("WebDriverException");
            log.info("Browser not launched");
            throw new RuntimeException();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;
    }

   public static String getPropertyValue(String key) throws Exception {
        String value = "";
        Properties property = new Properties();
        FileInputStream file = new FileInputStream(FrameworkContants.getPropertiesfile());
        property.load(file);
        value = property.getProperty(key);
        if (value == null) {
            throw new Exception("Property name " + key + " is not found. Please check data.properties file");
        }
        return value;
    }
    
    public static String getExcelValue(String sheetName,String key) throws Exception {
    	ArrayList<String> a=new ArrayList<String>();
        
        FileInputStream fis=new FileInputStream(FrameworkContants.getExcelFile());
        try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			int sheets=workbook.getNumberOfSheets();
			for(int i=0;i<sheets;i++)
			{
			if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
			{
			XSSFSheet sheet=workbook.getSheetAt(i);
			//Identify Testcases coloum by scanning the entire 1st row

			Iterator<Row>  rows= sheet.iterator();// sheet is collection of rows
			Row firstrow= rows.next();
			Iterator<Cell> ce=firstrow.cellIterator();//row is collection of cells
			int k=0;
			int coloumn = 0;
			while(ce.hasNext())
			{
			Cell value1=ce.next();

			if(value1.getStringCellValue().equalsIgnoreCase("TestCases"))
			{
			coloumn=k;

			}

			k++;
			}
			

			////once coloumn is identified then scan entire testcase coloum to identify purchase testcase row
			while(rows.hasNext())
			{

			Row r=rows.next();

			if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(key))
			{

			////after you grab purchase testcase row = pull all the data of that row and feed into test

			Iterator<Cell>  cv=r.cellIterator();
			while(cv.hasNext())
			{
			Cell c= cv.next();
			
			
			if(c.getCellType()==CellType.STRING)
			{

				a.add(c.getStringCellValue());
			}
			else{

				a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

			}
			}
			}
			}
			}
			}
		}

        if (a.get(1) == null) {
            throw new Exception("Property name " + key + " is not found. Please check data.properties file");
        }
        
        return a.get(1);
        
        }
    

    public static WebDriver getDriver() {
        return driver;
    }

    public String getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationPath = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationPath));
        return destinationPath;
    }

    public WebElement locateElement(String locator, String locValue) {
        try {
            switch (locator) {
                case ("id"):
                    return driver.findElement(By.id(locValue));
                case ("link"):
                    return driver.findElement(By.linkText(locValue));
                case ("xpath"):
                    return driver.findElement(By.xpath(locValue));
                case ("name"):
                    return driver.findElement(By.name(locValue));
                case ("class"):
                    return driver.findElement(By.className(locValue));
                case ("tag"):
                    return driver.findElement(By.tagName(locValue));
            }
        } catch (NoSuchElementException e) {
            log.error("The element with locator " + locator + " not found.");
        } catch (WebDriverException e) {
            log.error("Unknown exception occured while finding " + locator + " with the value " + locValue);
        }
        return null;
    }

    public List<WebElement> locateElements(String locator, String locValue) {
        try {
            switch (locator) {
                case ("id"):
                    return driver.findElements(By.id(locValue));
                case ("link"):
                    return driver.findElements(By.linkText(locValue));
                case ("xpath"):
                    return driver.findElements(By.xpath(locValue));
                case ("name"):
                    return driver.findElements(By.name(locValue));
                case ("class"):
                    return driver.findElements(By.className(locValue));
                case ("tag"):
                    return driver.findElements(By.tagName(locValue));
            }
        } catch (NoSuchElementException e) {
            log.error("The element with locator " + locator + " not found.");
        } catch (WebDriverException e) {
            log.error("Unknown exception occured while finding " + locator + " with the value " + locValue);
        }
        return null;
    }

    public WebElement locateElement(String locValue) {
        return driver.findElement(By.id(locValue));
    }

    public void type(WebElement ele, String data) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            ele.clear();
            ele.sendKeys(data);
            String x = "" + ele;
            log.info(data + " is entered in the text field");
        } catch (InvalidElementStateException e) {
            log.error(e + " is occurred during execution while entering " + data + " in the field");
        } catch (WebDriverException e) {
            log.error(e + " is occurred during execution while entering " + data + " in the field ");
        }
    }

    public void click(WebElement ele) {
        String text = "";
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            text = ele.getText();
            ele.click();
            log.info("The element " + text + " is clicked");
        } catch (InvalidElementStateException e) {
            log.error(e + " is occurred during execution when trying to click " + text);
        } catch (WebDriverException e) {
            log.error(e + " is occurred during execution when trying to click " + text);
        }
    }

    public String getText(WebElement ele) {
        String bReturn = "";
        try {
            bReturn = ele.getText();
        } catch (WebDriverException e) {
            log.error("The element: " + ele + " could not be found.");
        }
        return bReturn;
    }

    public String getTitle() {
        String bReturn = "";
        try {
            bReturn = driver.getTitle();
        } catch (WebDriverException e) {
            log.error(e + " Occured While fetching Title");
        }
        return bReturn;
    }

    public void selectDropDownUsingText(WebElement ele, String value) {
        try {
            new Select(ele).selectByVisibleText(value);
            log.info("The dropdown is selected with text " + value);
        } catch (WebDriverException e) {
            log.error(e + " Occured While selecting dropdown with text " + value);
        }

    }

    public void selectDropDownUsingIndex(WebElement ele, int index) {
        try {
            new Select(ele).selectByIndex(index);
            log.info("The dropdown is selected with index " + index);
        } catch (WebDriverException e) {
            log.error(e + " Occured While selecting dropdown with index " + index);
        }

    }


    public boolean verifyPartialTitle(String title) {
        boolean bReturn = false;
        try {
            if (getTitle().contains(title)) {
                log.info("The title of the page contains the text :" + title);
                bReturn = true;
                System.out.println("VerifyPartial Title Passed");
            } else {
                System.out.println("VerifyPartial Title failed");
                log.error("The title of the page:" + driver.getTitle() + " did not match with the value :" + title);
            }
        } catch (WebDriverException e) {
            log.error(e + " occured while verifying the title");
        }
        System.out.println(getTitle());
        return bReturn;

    }

    public void verifyExactText(WebElement ele, String expectedText) {
        try {
            if (getText(ele).equals(expectedText)) {
                log.info("The text: " + getText(ele) + " matches with the value :" + expectedText);
            } else {
                log.info("The text " + getText(ele) + " doesn't matches the actual " + expectedText);
            }
        } catch (WebDriverException e) {
            log.error(e + " occured while verifying the text");
        }

    }

    public void verifyPartialText(WebElement ele, String expectedText) {
        try {
            if (getText(ele).contains(expectedText)) {
                log.info("The expected text contains the actual " + expectedText);
            } else {
                log.info("The expected text doesn't contain the actual " + expectedText);
            }
        } catch (WebDriverException e) {
            log.error(e + " occured while verifying the text");
        }
    }

    public void verifySelected(WebElement ele) {
        try {
            if (ele.isSelected()) {
                log.info("The element " + ele + " is selected");
            } else {
                log.info("The element " + ele + " is not selected");
            }
        } catch (WebDriverException e) {
            log.error("WebDriverException : " + e.getMessage());
        }
    }

    public void verifyDisplayed(WebElement ele) {
        try {
            if (ele.isDisplayed()) {
                log.info("The element " + ele + " is visible");
            } else {
                log.info("The element " + ele + " is not visible");
            }
        } catch (WebDriverException e) {
            log.error("WebDriverException : " + e.getMessage());
        }
    }

    public void switchToWindow(int index) {
        try {
            Set<String> allWindowHandles = driver.getWindowHandles();
            List<String> allHandles = new ArrayList<>();
            allHandles.addAll(allWindowHandles);
            driver.switchTo().window(allHandles.get(index));
        } catch (NoSuchWindowException e) {
            log.error("The driver could not move to the given window by index " + index);
        } catch (WebDriverException e) {
            log.error("WebDriverException : " + e.getMessage());
        }
    }

    public void switchToFrame(WebElement ele) {
        try {
            driver.switchTo().frame(ele);
            log.info("switch In to the Frame " + ele);
        } catch (NoSuchFrameException e) {
            log.info("NoSuchFrameException : " + e.getMessage());
        } catch (WebDriverException e) {
            log.info("WebDriverException : " + e.getMessage());
        }
    }

    public void acceptAlert() {
        String text = "";
        try {
            Alert alert = driver.switchTo().alert();
            text = alert.getText();
            alert.accept();
            log.info("The alert " + text + " is accepted.");
        } catch (NoAlertPresentException e) {
            log.info("There is no alert present.");
        } catch (WebDriverException e) {
            log.info("WebDriverException : " + e.getMessage());
        }
    }

    public void dismissAlert() {
        String text = "";
        try {
            Alert alert = driver.switchTo().alert();
            text = alert.getText();
            alert.dismiss();
            log.info("The alert " + text + " is dismissed.");
        } catch (NoAlertPresentException e) {
            log.info("There is no alert present.");
        } catch (WebDriverException e) {
            log.info("WebDriverException : " + e.getMessage());
        }

    }

    public String getAlertText() {
        String text = "";
        try {
            Alert alert = driver.switchTo().alert();
            text = alert.getText();
        } catch (NoAlertPresentException e) {
            log.info("There is no alert present.");
        } catch (WebDriverException e) {
            log.info("WebDriverException : " + e.getMessage());
        }
        return text;
    }

    public void mouseOver(WebElement ele) {
        try {
            Actions ac = new Actions(driver);
            ac.moveToElement(ele).perform();
            log.info("Performed Mouse over action successfully");
        } catch (Exception e) {
            log.error("Mouse over is not working");
        }
    }


}
