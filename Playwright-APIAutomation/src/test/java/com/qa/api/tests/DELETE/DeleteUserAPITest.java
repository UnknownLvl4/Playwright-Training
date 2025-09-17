package com.qa.api.tests.DELETE;

import com.api.data.User;
import com.api.data.Users;
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

public class DeleteUserAPITest {
    //1. Create a user -- user id -- 201
    //2. Delete user -- user id -- 204
    //3. Get user -- user id --404

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
    public void deleteUserTest() throws IOException {

        //1. Create users object: using builder pattern:
        Users users = Users.builder()
                .name("Duy Automation")
                .email(getRandomEmail())
                .gender("male")
                .status("active").build();

        //POST Call: create a user
        APIResponse apiPostResponse = requestContext.post("https://gorest.co.in/public/v2/users/", RequestOptions.create()
                .setHeader("content-type", "application/json")
                .setHeader("Authorization", "Bearer 3644af0942c7e1088d725459dd7c4b3afa90decde4e2bb9a867127ce1fb8c2d7")
                .setData(users));

        System.out.println(apiPostResponse.url());
        System.out.println(apiPostResponse.status());
        Assert.assertEquals(apiPostResponse.status(), 201);

        String responseText = apiPostResponse.text();
        System.out.println(responseText);

        //Convert response text/json to POJO -- desrialization
        ObjectMapper objectMapper = new ObjectMapper();
        User actUser = objectMapper.readValue(responseText, User.class);
        System.out.println("actual user from the response---->");
        System.out.println(actUser);

        Assert.assertNotNull(actUser.getId());

        String userId = actUser.getId();
        System.out.println("new user id is :" + userId);

        //2. Delete user -- user id -- 204
        APIResponse apiDELETEResponse = requestContext.delete("https://gorest.co.in/public/v2/users/" + userId, RequestOptions.create()
                .setHeader("Authorization", "Bearer 3644af0942c7e1088d725459dd7c4b3afa90decde4e2bb9a867127ce1fb8c2d7"));

        System.out.println(apiDELETEResponse.status());
        System.out.println(apiDELETEResponse.statusText());

        Assert.assertEquals(apiDELETEResponse.status(), 204);

        System.out.println("delete user response body ====" + apiDELETEResponse.text());

        //3. Get user -- user id -- 404
        APIResponse apiResponse = requestContext.get("https://gorest.co.in/public/v2/users/" + userId, RequestOptions.create()
                .setHeader("Authorization", "Bearer 3644af0942c7e1088d725459dd7c4b3afa90decde4e2bb9a867127ce1fb8c2d7"));

        System.out.println(apiResponse.text());

        int statusCode = apiResponse.status();
        System.out.println("response status code: " + statusCode);
        Assert.assertEquals(statusCode, 404);
        Assert.assertEquals(apiResponse.statusText(), "Not Found");
        Assert.assertTrue(apiResponse.text().contains("Resource not found"));
    }
}