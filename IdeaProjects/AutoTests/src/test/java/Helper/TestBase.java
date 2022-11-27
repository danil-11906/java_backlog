package Helper;

import org.junit.Before;

import static org.junit.Assert.fail;

public class TestBase{

    public static ApplicationManager manager;

    @Before
    public void setUp() {
        manager = ApplicationManager.getInstance();
    }

    protected static void down() {
        manager.Stop();
    }

}