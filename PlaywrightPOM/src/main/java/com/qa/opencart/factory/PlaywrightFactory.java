package com.qa.opencart.factory;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;

    public Page initBrowser(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser name is : " + browserName);
        playwright = Playwright.create();
        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
                break;
            default:
                System.out.println("please pass the right browser name......");
                break;
        }
        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate(prop.getProperty("url").trim());

        return page;
    }
    /*
        this method is used to initialize the properties from config file
     */
    public Properties init_prop() {
        try {
            FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(ip);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prop;
    }
}