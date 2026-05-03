package AutomationExercise.DriverManagerLayer;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.HashMap;
import java.util.Map;

//enum-based Driver Manager pattern

public enum BrowserFactory {
    CHROME {

        @Override
        public WebDriver createDriver() {
            return new ChromeDriver(getOptions());
        }
        @Override
        public ChromeOptions getOptions() {
            ChromeOptions options  = new ChromeOptions();
            // Website preferences
            Map<String, Object> prefs  = new HashMap<>();
            // 0 -> Ask (default)  1 -> Allow  2 -> Block  Notifications
            prefs.put("profile.default_content_setting_values.notifications", 2);
            //disable the "Save password"
            prefs.put("credentials_enable_service", false);
            //disable password saving and autofill features
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("autofill.profile_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            options.addArguments("--start-maximized");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");
           // options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            options.addArguments("--remote-debugging-pipe");
            if (System.getProperty("headless") != null) {
                options.addArguments("--headless=new");
            }
            //accept all insecure SSL certificates without showing any security warnings or halting the automation
            options.setAcceptInsecureCerts(true);

            return options;
        }
    },
    EDGE {
        @Override
        public WebDriver createDriver() {
            return new EdgeDriver(getOptions());
        }

        @Override
        public EdgeOptions getOptions() {
            EdgeOptions options = new EdgeOptions();
            Map<String, Object> preferences = new HashMap<>();
            preferences.put("profile.default_content_setting_values.notifications", 2);
            preferences.put("credentials_enable_service", false);
            preferences.put("profile.password_manager_enabled", false);
            preferences.put("autofill.profile_enabled", false);
            options.setExperimentalOption("preferences", preferences);
            options.addArguments("--start-maximized");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--remote-debugging-pipe");
            if (System.getProperty("headless") != null) {
                options.addArguments("--headless=new");
            }
            options.setAcceptInsecureCerts(true);
            return  options;
        }
    };

    public abstract WebDriver createDriver();
    public abstract MutableCapabilities getOptions();

}

