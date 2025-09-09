package PlaywrightSessions;

import com.microsoft.playwright.*;

public class NthElementSelector {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.swiggy.com/");

        Locator first_ele = page.locator("div.rzOTr li a >> nth=0");
        String firstEle = first_ele.textContent();
        System.out.println(firstEle);
        first_ele.click();

//        Locator last_ele = page.locator("div.rzOTr li a >> nth=-1");
//        String lastEle = last_ele.textContent();
//        System.out.println(lastEle);
    }
}