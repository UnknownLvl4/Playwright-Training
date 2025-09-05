package PlaywrightSessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.util.List;

public class VisibleElements {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        //button:visible
        //button >> visible=true

//        page.navigate("D:\\Playwright-Training\\PlaywrightSessions\\index.html");
////        String text = page.locator("button:visible").textContent();
//        String text = page.locator("button >> visible=true").textContent();
//        System.out.println(text);

        page.navigate("https://www.amazon.com/");
        page.locator(".a-button-text").click();
        page.waitForTimeout(1000);

        List<String> linksText = page.locator("a:visible").allInnerTexts();
        for (int i = 0; i < linksText.size(); i++) {
            System.out.println(linksText.get(i));
        }

        int imagesCount = page.locator("xpath=//img >> visible=true").count();
        System.out.println(imagesCount);

        //text=Login >> visible=true
    }
}