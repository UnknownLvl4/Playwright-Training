package com.qa.api.booking;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TokenTest {
    Playwright playwright;
    APIRequest request;
    APIRequestContext requestContext;
    static String emailId;

    @BeforeTest
    public void setup() {
        playwright = Playwright.create();
        request = playwright.request();
        requestContext = request.newContext();
    }

    @AfterTest
    public void tearDown() {
        playwright.close();
    }

    @Test
    public void getTokenTest() throws IOException {

        //String Json:
        String reqTokenJsonBody = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        //POST Call: create a token
        APIResponse apiPostTokenResponse = requestContext.post("https://restful-booker.herokuapp.com/auth/", RequestOptions.create()
                .setHeader("content-type", "application/json")
                .setData(reqTokenJsonBody));

        System.out.println(apiPostTokenResponse.status());
        Assert.assertEquals(apiPostTokenResponse.status(), 200);
        Assert.assertEquals(apiPostTokenResponse.statusText(), "OK");

        System.out.println(apiPostTokenResponse.text());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode postJsonResponse = objectMapper.readTree(apiPostTokenResponse.body());
        System.out.println(postJsonResponse.toPrettyString());

        //Capture token from the post json response:
        String tokenId = postJsonResponse.get("token").asText();
        System.out.println("token id : " + tokenId);

        Assert.assertNotNull(tokenId);
    }
}