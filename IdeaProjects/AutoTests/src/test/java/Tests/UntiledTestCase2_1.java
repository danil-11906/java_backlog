package Tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import Helper.TestBase;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class UntiledTestCase2_1 extends TestBase {
    @Test
    public void testUntitledTestCase() throws Exception {
        TestBase.manager.getNavigationHelper().OpenHomePage();
        TestBase.manager.getOrderHelper().NewOrder();
    }
}