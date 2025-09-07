package PlaywrightSessions;

import com.microsoft.playwright.*;

public class CommaSelectors {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://academy.naveenautomationlabs.com/");
        //open Playwright inspector -> go to website -> open tab Console in Chrome Dev Tools:
            //playwright.$("a:has-text('Login')")
            //playwright.$("a:has-text('Login'), a:has-text('SignIn')")
            //playwright.$("a:has-text('Login'), a:has-text('SignIn'), a:has-text('Signin'), a:has-text('LogIn')")

//        page.locator("a:has-text('Login'), " +
//                "a:has-text('SignIn'), " +
//                "a:has-text('Signin'), " +
//                "a:has-text('LogIn'), " +
//                "a:has-text('signin')").click();

            //playwright.$("a:has-text('Webinars'), a:has-text('Login')")
            //playwright.$$("a:has-text('Webinars'), a:has-text('Login')")

        Locator imp_ele = page.locator("a:has-text('Webinars'), " +
                "a:has-text('Login'), " +
                "a:has-text('EXPLORE COURSES')");
        System.out.println(imp_ele.count());
        if (imp_ele.count() == 3) {
            System.out.println("PASS");
        }
        else {
            System.out.println("FAIL");
        }
    }
}