package PlaywrightSessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FillFormUsingRelativeLocators {
    static Page page;

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://www.orangehrm.com/30-day-free-trial");
        page.waitForTimeout(14000);
        //Chrome Dev Tools:
        page.locator("input:below(label:text('Full Name'))").first().fill("Duy");
        page.locator("input:below(label:text('Email'))").first().fill("Duy@gmail.com");
        page.locator("input:below(label:text('Phone Number'))").first().fill("Duy");
    }
}