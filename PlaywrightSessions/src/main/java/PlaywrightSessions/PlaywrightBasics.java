package PlaywrightSessions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class PlaywrightBasics {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();

//        BrowserType.LaunchOptions lp = new BrowserType.LaunchOptions();
//        lp.setChannel("chrome");
//        lp.setHeadless(false);
//        Browser browser = playwright.chromium().launch(lp);
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.amazon.com/");
        page.pause();

        String title = page.title();
        System.out.println("page title is : " + title);

        String url = page.url();
        System.out.println("url is : " + url);

        browser.close();
        playwright.close();
    }
}