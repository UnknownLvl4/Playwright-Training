package PlaywrightSessions;

import com.microsoft.playwright.*;
import java.util.List;

public class LocatorConcept {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        //locators

        BrowserContext browserContext = browser.newContext();
        Page page = browserContext.newPage();

        //single element:
//        page.navigate("https://www.orangehrm.com/orangehrm-30-day-trial");
//        Locator contactSales = page.locator("text = OUR OFFICES");
//        contactSales.hover();
//        contactSales.click();

//        page.navigate("https://academy.naveenautomationlabs.com/");
//        Locator loginBtn=page.locator("text = Login").first();
//        loginBtn.hover();
//        loginBtn.click();

        //multiple elements:
//        page.navigate("https://academy.naveenautomationlabs.com/");
//        Locator loginBtn = page.locator("text = Login");
//        int totalLogins = loginBtn.count();
//        System.out.println(totalLogins);
//        loginBtn.first().last();

        //multiple elements:
        page.navigate("https://www.orangehrm.com/orangehrm-30-day-trial");
        Locator countryOptions = page.locator("select#Form_getForm_Country option");
        System.out.println(countryOptions.count());

//        for (int i = 0; i < countryOptions.count(); i++) {
//            String text = countryOptions.nth(i).textContent();
//            System.out.println(text);
//        }

        List<String> optionsTextList = countryOptions.allTextContents();
//        for (String e : optionsTextList) {
//            System.out.println(e);
//        }

        optionsTextList.forEach(ele -> System.out.println(ele));
    }
}