package Helper;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase{

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }

    public void Login() {
        WriteLogin();
        WritePassword();
        driver.findElement(By.id("link_login")).click();
    }

    public void WritePassword() {
        driver.findElement(By.id("user_password")).click();
        driver.findElement(By.id("user_password")).clear();
        driver.findElement(By.id("user_password")).sendKeys("qwerty007");
    }

    public void WriteLogin() {
        driver.findElement(By.id("link-login")).click();
        driver.findElement(By.id("user_email")).click();
        driver.findElement(By.id("user_email")).clear();
        driver.findElement(By.id("user_email")).sendKeys("danil.barkov00@mail.ru");
    }
}
