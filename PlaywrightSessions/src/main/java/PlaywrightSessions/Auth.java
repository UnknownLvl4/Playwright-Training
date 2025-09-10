package PlaywrightSessions;

import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class Auth {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext brContext = browser.newContext();
        Page page = brContext.newPage();
        page.navigate("http://www.automationpractice.pl/");
        page.click("a:text('Sign in')");
        page.fill("#email", "xyz112@gmail.com");
        page.fill("#passwd", "zzz579314");
        page.click("#SubmitLogin");
        brContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("applogin.json")));
    }
}