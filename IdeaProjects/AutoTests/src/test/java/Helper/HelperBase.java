package Helper;

import org.openqa.selenium.WebDriver;


public class HelperBase{

    protected ApplicationManager manager;
    protected WebDriver driver;
    public String baseUrl = "https://www.220-volt.ru";

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
        this.driver = manager.getDriver();
    }
}
