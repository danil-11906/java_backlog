package Tests;

import Helper.TestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UntitledTestCase2 extends TestBase {
  @Test
  public void testUntitledTestCase(){
    TestBase.manager.getNavigationHelper().OpenHomePage();
    TestBase.manager.getLoginHelper().Login();
  }

}
