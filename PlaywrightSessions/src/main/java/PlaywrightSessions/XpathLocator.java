package PlaywrightSessions;

import com.microsoft.playwright.*;
import java.util.List;

public class XpathLocator {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

//        page.navigate("https://www.amazon.com/");
//        page.locator(".a-button-text").click();
//        page.waitForTimeout(1000);
//        page.reload();

//        page.locator("xpath=//input[@id='twotabsearchtextbox']").fill("Macbook pro");
//        page.locator("//input[@id='twotabsearchtextbox']").fill("Macbook pro");

//        Locator totalAmazonLinks = page.locator("//a[contains(text(), 'Amazon')]");
//        System.out.println(totalAmazonLinks.count());
//        List<String> textList = totalAmazonLinks.allInnerTexts();
//        for (String e : textList) {
//            System.out.println(e);
//        }

//        page.locator("//a[text()='Joe.Root']/parent::td/preceding-sibling::td/input[@type='checkbox']").click();

        page.navigate("https://selectorshub.com/xpath-practice-page/");

//        Locator checkboxes = page.locator("//table[@id='resultTable']//input[@type='checkbox']");
//        for (int i = 1; i < checkboxes.count(); i++) {
//            checkboxes.nth(i).click();
//        }

        page.locator("xpath=(//table[@id='resultTable']//input[@type='checkbox'])[1]").click();
        page.locator("(//table[@id='resultTable']//input[@type='checkbox'])[last()]").click();
        page.locator("(//table[@id='resultTable']//input[@type='checkbox'])[position()=4]").click();
    }
}