package PlaywrightSessions;

import com.microsoft.playwright.*;

public class TextSelector {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        //text locators
        Page page = browser.newPage();
//        page.navigate("https://www.orangehrm.com/orangehrm-30-day-trial");
//        page.locator("text=Our Offices").click();

//        page.locator("text=Privacy Policy").first().click();

        Locator links = page.locator("text=Privacy Policy");
//        for (int i = 0; i < links.count(); i++) {
//            String text = links.nth(i).textContent();
//            if (text.contains("Service Privacy Policy")) {
//                links.nth(i).click();
//                break;
//            }
//        }

        page.navigate("https://demo.opencart.com/index.php?route=account/login");
        String header = page.locator("div.well h2:has-text('New Customer')").textContent();
        System.out.println(header);

//        String pageHeader = page.locator("'Your Store'").textContent();
        String pageHeader = page.locator("text=Your Store").first().textContent();
        System.out.println(pageHeader);

        //button
        page.locator("form input:has-text=('Login'))").click();
    }
}