package PlaywrightSessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class NearLocatorRelative {
    static Page page;

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("http://www.automationpractice.pl/index.php?id_category=3&controller=category");
        //Chrome Dev Tools:
        page.locator("input:near(:text('Newsletter'))").fill("Duy@gmail.com");
        String price = page.locator("span.price.product-price:below(:text('Faded Short Sleeve T-shirts'))").first().innerText().trim();
        System.out.println(price);
    }
}