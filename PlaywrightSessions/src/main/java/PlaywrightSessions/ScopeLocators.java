package PlaywrightSessions;

import com.microsoft.playwright.*;

public class ScopeLocators {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

//        page.navigate("https://datatables.net/extensions/select/examples/checkbox/checkbox.html");
//        Locator row = page.locator("table#example tr");
//        row.locator(":scope", new Locator.LocatorOptions().setHasText("Ashton Cox")).locator(".dt-select").click();
//        row.locator(":scope").allInnerTexts().forEach(e -> System.out.println(e));

        page.navigate("https://primeng.org/table#customers");
        Locator row = page.locator("table#pn_id_57-table tr");
        row.locator(":scope", new Locator.LocatorOptions().setHasText("James Butt")).locator(".p-checkbox-input").click();
        row.locator(":scope").allInnerTexts().forEach(e -> System.out.println(e));
    }
}