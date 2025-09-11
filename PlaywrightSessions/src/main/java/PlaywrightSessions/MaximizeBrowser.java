package PlaywrightSessions;

import com.microsoft.playwright.*;
import java.awt.*;

public class MaximizeBrowser {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        System.out.println(width + ":" + height);
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

//        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1366,768));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));

        Page page = context.newPage();
        page.navigate("https://www.amazon.com/");
    }
}