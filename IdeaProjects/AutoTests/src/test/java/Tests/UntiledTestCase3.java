package Tests;

import Helper.TestBase;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UntiledTestCase3 extends TestBase {

    @Test
    public void auth() {
        TestBase.manager.getNavigationHelper().OpenHomePage();
        TestBase.manager.getLoginHelper().Login();
    }

    @Test
    public void newOrder() {
        String t = TestBase.manager.getOrderHelper().NewOrder();
        assertEquals(manager.orderHelper.orderData(), t);
    }

    @Test
    public void updateOrder() {
        String t = TestBase.manager.getOrderHelper().UpdateOrder();
        assertEquals(manager.orderHelper.orderData(), t);
    }

    @AfterClass
    public static void onDestroy() {
        TestBase.down();
    }
}
