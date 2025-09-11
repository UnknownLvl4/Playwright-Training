package PlaywrightSessions;

import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class DownloadFile {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://chromedriver.storage.googleapis.com/index.html?path=114.0.5735.90/");
        Download download = page.waitForDownload(() -> {
            page.click("a:text('chromedriver_win32.zip')");
        });

//        download.cancel();
        System.out.println(download.failure());

        System.out.println(download.url());
//        System.out.println(download.page().title());

        String path = download.path().toString();
        System.out.println(path);
        download.saveAs(Paths.get("Duy.zip"));
        System.out.println(download.suggestedFilename()); //chromedriver_win32.zip
    }
}