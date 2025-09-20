package PlaywrightSessions;

import com.microsoft.playwright.*;

public class BrowserContextConcept {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        //Open multiple browser:
//        BrowserContext brcx1 = browser.newContext();
//        Page p1 = brcx1.newPage();
//        p1.navigate("https://www.orangehrm.com/orangehrm-30-day-trial");
//        p1.fill("#Form_getForm_subdomain", "Ngocduy");
//        System.out.println(p1.title());
//
//        BrowserContext brcx2 = browser.newContext();
//        Page p2 = brcx2.newPage();
//        p2.navigate("https://automationexercise.com/products");
//        p2.fill("#search_product", "Dress");
//        p2.click("#submit_search");
//        System.out.println(p2.title());
//
//        BrowserContext brcx3 = browser.newContext();
//        Page p3 = brcx3.newPage();
//        p3.navigate("https://www.google.com/");
//        System.out.println(p3.title());
//
//        p1.close();
//        brcx1.close();
//
//        p2.close();
//        brcx2.close();
//
//        p3.close();
//        brcx3.close();

        //Open multiple tabs:
//        BrowserContext context = browser.newContext();
//
//        Page page1 = context.newPage();
//        page1.navigate("https://www.orangehrm.com/orangehrm-30-day-trial");
//        System.out.println("Tab 1 Title: " + page1.title());
//
//        Page page2 = context.newPage();
//        page2.navigate("https://automationexercise.com/products");
//        System.out.println("Tab 2 Title: " + page2.title());
//
//        Page page3 = context.newPage();
//        page3.navigate("https://www.google.com");
//        System.out.println("Tab 3 Title: " + page3.title());
//
//        //List all tabs in the same context
//        for (Page p : context.pages()) {
//            System.out.println("Found tab with URL: " + p.url());
//        }
//
//        browser.close();

        //Open multiple windows:
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://www.orangehrm.com/orangehrm-30-day-trial");
        System.out.println("Main Tab Title: " + page.title());

        Page newTab = context.waitForPage(() -> {
            page.click("a[href*='privacy-policy']");
        });

        //Switch focus to new tab
        newTab.waitForLoadState(); //Wait for new tab completely loaded
        System.out.println("New Tab Title: " + newTab.title());
        System.out.println("New Tab URL: " + newTab.url());

        //Go back to main page
        page.bringToFront(); //Bring main tab to the front
        page.fill("#Form_getForm_subdomain", "Ngocduy");
        System.out.println("Back to Main Tab and filled the form.");

        browser.close();
    }
}