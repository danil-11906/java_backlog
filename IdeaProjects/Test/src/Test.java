package com.example;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class UntitledTestCase {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;
    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSft4UiVYvZBlehTbyvMhBkfKjW_eH1le6seeP1iMS7j7ZsIhQ/viewform");
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSft4UiVYvZBlehTbyvMhBkfKjW_eH1le6seeP1iMS7j7ZsIhQ/viewform");
        driver.findElement(By.xpath("//input[@type='text']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).clear();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("500");
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div/input")).click();
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div/input")).clear();
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div/input")).sendKeys("3000000");
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[2]/div[3]/div/div/div[2]/div/span/div/label/div[2]/div/div/div[3]/div")).click();
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[2]/div[3]/div/div/div[2]/div/span/div/label[3]/div[2]/div/div/div[3]")).click();
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[2]/div[4]/div/div/div[2]/div/span/div/label[3]/div[2]/div/div/div[3]")).click();
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[2]/div[5]/div/div/div[2]/div/span/div/label[3]/div[2]/div/div/div[3]/div")).click();
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[2]/div[6]/div/div/div[2]/div/span/div/label[3]/div[2]/div/div/div[3]")).click();
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[2]/div[7]/div/div/div[2]/div/span/div/label[3]/div[2]/div/div/div[3]/div")).click();
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[2]/div[8]/div/div/div[2]/div/span/div/label[10]/div[2]/div/div/div[3]")).click();
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[2]/div[9]/div/div/div[2]/div/span/div/label[10]/div[2]/div/div/div[3]/div")).click();
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[2]/div[10]/div/div/div[2]/div/span/div/label[10]/div[2]/div/div/div[3]/div")).click();
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[2]/div[11]/div/div/div[2]/div/span/div/label[3]/div[2]/div/div/div[3]")).click();
        driver.findElement(By.xpath("//form[@id='mG61Hd']/div[2]/div/div[3]/div/div/div/span/span")).click();
        driver.get("https://docs.google.com/forms/u/0/d/e/1FAIpQLSft4UiVYvZBlehTbyvMhBkfKjW_eH1le6seeP1iMS7j7ZsIhQ/formResponse");
        driver.close();
        //ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
