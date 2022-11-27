package Helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private static ThreadLocal<ApplicationManager> app = new ThreadLocal<ApplicationManager>();

    private static WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors;
    JavascriptExecutor js;

    public LoginHelper loginHelper;
    public NavigationHelper navigationHelper;
    public OrderHelper orderHelper;

    public static ApplicationManager getInstance() {
        ApplicationManager applicationManager = app.get();
        if (applicationManager == null) {
            app.set(new ApplicationManager());
        }
        return app.get();
    }

    private ApplicationManager() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tron5\\Documents\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
        verificationErrors = new StringBuffer();
        js = (JavascriptExecutor) driver;
        loginHelper = new LoginHelper(this);
        navigationHelper = new NavigationHelper(this);
        orderHelper = new OrderHelper(this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public LoginHelper getLoginHelper() {
        return loginHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public OrderHelper getOrderHelper() {
        return orderHelper;
    }

    public static void Stop(){
        driver.quit();
    }
}
