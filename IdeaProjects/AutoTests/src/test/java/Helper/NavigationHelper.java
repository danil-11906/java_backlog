package Helper;


public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void OpenHomePage() {
        driver.get("https://kazan.220-volt.ru/");
    }
}
