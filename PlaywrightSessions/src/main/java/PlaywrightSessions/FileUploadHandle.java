package PlaywrightSessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.FilePayload;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadHandle {
    public static void main(String[] args) throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
//        page.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");
        //input type = file

        //select one file:
//        page.setInputFiles("input#filesToUpload", Paths.get("applogin.json"));
//        Thread.sleep(4000);
//        page.setInputFiles("input#filesToUpload", new Path[0]);


//        page.setInputFiles("input#filesToUpload", new Path[]{
//                Paths.get("applogin.json"),
//                Paths.get("trace.zip"),
//                Paths.get("README.md")
//        });
//        Thread.sleep(3000);
//        page.setInputFiles("input#filesToUpload", new Path[0]);

        //run time file - upload:
//        page.setInputFiles("input#filesToUpload", new FilePayload("duy.text", "text/plain", "this is duy here".getBytes(StandardCharsets.UTF_8)));

        page.navigate("https://cgi-lib.berkeley.edu/ex/fup.html");
        page.setInputFiles("input[name='upfile']", new FilePayload("duy.txt", "text/plain", "this is duy here".getBytes(StandardCharsets.UTF_8)));
        page.click("input[value='Press']");
    }
}