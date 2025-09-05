package PlaywrightSessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.util.List;

public class TotalAmazonLinks {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://www.amazon.com/");
        page.locator(".a-button-text").click();
        page.waitForTimeout(1000);
        //open Playwright inspector -> go to website -> open tab Console in Chrome Dev Tools:
            //playwright.$("text=Registry")
            //playwright.$$("text=Registry")
            //playwright.inspect("text=Registry")
            //playwright.$("input#twotabsearchtextbox")
            //playwright.inspect("input#twotabsearchtextbox")
            //playwright.$("//input[@id='twotabsearchtextbox']")
            //playwright.$$("a:has-text('Amazon')")
            //playwright.$("//a[@class='a-button-text']")
            //playwright.$("//a[@class='a-button-text']/span")
            //playwright.$(":has-text('Amazon')")
            //playwright.$$(":has-text('Amazon')")

        List<String> amazonLinksList=page.locator("a:has-text('Amazon')").allInnerTexts();
        amazonLinksList.stream().forEach(e-> System.out.println(e));

//        page.navigate("https://www.orangehrm.com/30-day-free-trial");
            //playwright.$("#Form_getForm_Name")
            //playwright.inspect("#Form_getForm_Name")
            //playwright.$("input#Form_getForm_Name >> visible=true")
    }
}