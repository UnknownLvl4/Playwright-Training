package PlaywrightSessions;

import com.microsoft.playwright.*;

public class XpathUnion {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        //text locators
        Page page = browser.newPage();
        page.navigate("https://academy.naveenautomationlabs.com/");
        //open Playwright inspector -> go to website -> open tab Console in Chrome Dev Tools:
            //playwright.$("//a[text()='Login'] | //a[text()='signin']")

        Locator login = page.locator("//a[text()='Login'] | //a[text()='signin']");
        System.out.println(login.textContent());
        login.click();
    }
}