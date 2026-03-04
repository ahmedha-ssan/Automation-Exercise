package AutomationExercise.Pages;

import org.openqa.selenium.By;

import static AutomationExercise.DriverManagerLayer.DriverManager.getDriver;
import static AutomationExercise.Utilities.Utility.clicking;
import static AutomationExercise.Utilities.Utility.reloadPage;

public class P_AdPage {

    public static P_AdPage closeAdByRefreshing(By element) {
        reloadPage();
        try {
            clicking(getDriver(), element);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new P_AdPage();
    }
}
