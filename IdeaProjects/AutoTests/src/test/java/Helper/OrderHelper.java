package Helper;

import org.openqa.selenium.By;

public class OrderHelper extends HelperBase{

    public OrderHelper(ApplicationManager manager) {
        super(manager);
    }

    public String NewOrder() {
        driver.get(baseUrl + "/catalog");
        driver.findElement(By.name("q")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("737032");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        return driver.findElement(By.id("product-code")).getText();
    }

    public String UpdateOrder(){
        driver.get(baseUrl + "/catalog");
        driver.findElement(By.name("q")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("653290");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        return driver.findElement(By.id("product-code")).getText();
    }

    public String orderData() {
        driver.findElement(By.id("bQuantityTitle")).click();
        return driver.findElement(By.id("product-code")).getText();
    }
}
