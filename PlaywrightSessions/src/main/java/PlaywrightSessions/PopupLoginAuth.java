package PlaywrightSessions;

import com.microsoft.playwright.*;

public class PopupLoginAuth {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin", "admin"));
        Page page = context.newPage();
        page.navigate("https://the-internet.herokuapp.com/basic_auth");

        System.out.println("Page content: " + page.textContent("body"));
    }
}