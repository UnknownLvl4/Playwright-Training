package PlaywrightSessions;

import com.microsoft.playwright.*;

public class HasElements {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
//        page.navigate("https://www.orangehrm.com/30-day-free-trial");
        //open Playwright inspector -> go to website -> open tab Console in Chrome Dev Tools:
            //playwright.$("select#Form_getForm_Country:has(option[value='Vietnam'])")

//        Locator loc = page.locator("select#Form_getForm_Country:has(option[value='Vietnam'])");
//        loc.allInnerTexts().forEach(e -> System.out.println(e));

        page.navigate("https://www.amazon.com/");
        page.locator(".a-button-text").click();
        page.waitForTimeout(1000);
            //playwright.$("div.navFooterLinkCol:has(a[href='https://www.amazon.jobs'])")
        Locator footerList = page.locator("div.navFooterLinkCol:has(a[href='https://www.amazon.jobs'])");
        footerList.allInnerTexts().forEach(e -> System.out.println(e));
    }
}