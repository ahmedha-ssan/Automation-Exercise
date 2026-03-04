package AutomationExercise.DriverManagerLayer;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    //each test thread gets its own WebDriver instance
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager(){
        super();
    }
    public static WebDriver getDriver(){
//        if(driverThreadLocal.get() == null){
//            driverThreadLocal.set(getDriver());
//        }
        return driverThreadLocal.get();
    }
    public static void setDriver(WebDriver driver) {
        DriverManager.driverThreadLocal.set(driver);
    }
    public static void quit() {
        if (getDriver() != null) {
            getDriver().quit();
            driverThreadLocal.remove();
        }
    }
}
