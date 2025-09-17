package com.qa.api.tests.POST;

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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CreateUserWithJsonFileTest {
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

    public static String getRandomEmail() {
        emailId = "testpwautomation" + System.currentTimeMillis() + "@gmail.com";
        return emailId;
    }

    @Test
    public void createUserTest() throws IOException {

        //Get Json file:
        byte[] fileBytes = null;
        File file = new File("./src/test/data/user.json");
        Files.readAllBytes(file.toPath());
        fileBytes = Files.readAllBytes(file.toPath());

        //POST Call: create a user
        APIResponse apiPostResponse = requestContext.post("https://gorest.co.in/public/v2/users/", RequestOptions.create()
                .setHeader("content-type", "application/json")
                .setHeader("Authorization", "Bearer 3644af0942c7e1088d725459dd7c4b3afa90decde4e2bb9a867127ce1fb8c2d7")
                .setData(fileBytes));

        System.out.println(apiPostResponse.status());
        Assert.assertEquals(apiPostResponse.status(), 201);
        Assert.assertEquals(apiPostResponse.statusText(), "Created");

        System.out.println(apiPostResponse.text());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode postJsonResponse = objectMapper.readTree(apiPostResponse.body());
        System.out.println(postJsonResponse.toPrettyString());

        //Capture id frome the post json response:
        String userId = postJsonResponse.get("id").asText();
        System.out.println("user id : " + userId);

        //GET Call: Fetch the same user by id:
        System.out.println("=================get call response===================");
        APIResponse apiGetResponse = requestContext.get("https://gorest.co.in/public/v2/users/" + userId, RequestOptions.create().setHeader("Authorization", "Bearer 3644af0942c7e1088d725459dd7c4b3afa90decde4e2bb9a867127ce1fb8c2d7"));

        Assert.assertEquals(apiGetResponse.status(), 200);
        Assert.assertEquals(apiGetResponse.statusText(), "OK");
        System.out.println(apiGetResponse.text());
        Assert.assertTrue(apiGetResponse.text().contains(userId));
        Assert.assertTrue(apiGetResponse.text().contains("Duytesting"));

//        Assert.assertTrue(apiGetResponse.text().contains(emailId));
    }
}