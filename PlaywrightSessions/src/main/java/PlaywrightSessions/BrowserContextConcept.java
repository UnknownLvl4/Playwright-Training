package PlaywrightSessions;

import com.microsoft.playwright.*;

public class BrowserContextConcept {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext brcx1 = browser.newContext();
        Page p1 = brcx1.newPage();
        p1.navigate("https://www.orangehrm.com/orangehrm-30-day-trial");
        p1.fill("#Form_getForm_subdomain", "Ngocduy");
        System.out.println(p1.title());

        BrowserContext brcx2 = browser.newContext();
        Page p2 = brcx2.newPage();
        p2.navigate("https://automationexercise.com/products");
        p2.fill("#search_product", "Dress");
        p2.click("#submit_search");
        System.out.println(p2.title());

        BrowserContext brcx3 = browser.newContext();
        Page p3 = brcx3.newPage();
        p3.navigate("https://www.google.com/");
        System.out.println(p3.title());

        p1.close();
        brcx1.close();

        p2.close();
        brcx2.close();

        p3.close();
        brcx3.close();
    }
}