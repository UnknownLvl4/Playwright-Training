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

public class BookingTest {
    Playwright playwright;
    APIRequest request;
    APIRequestContext requestContext;
    private static String TOKEN_ID = null;

    @BeforeTest
    public void setup() throws IOException {
        playwright = Playwright.create();
        request = playwright.request();
        requestContext = request.newContext();

        //Get the token:
        String reqTokenJsonBody = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        //POST Call: create a token
        APIResponse apiPostTokenResponse = requestContext.post("https://restful-booker.herokuapp.com/auth", RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(reqTokenJsonBody));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode postJsonResponse = objectMapper.readTree(apiPostTokenResponse.body());
        System.out.println(postJsonResponse.toPrettyString());

        //Capture token from the post json response:
        TOKEN_ID = postJsonResponse.get("token").asText();
        System.out.println("token id : " + TOKEN_ID);
    }

    @AfterTest
    public void tearDown() {
        playwright.close();
    }

    @Test
    public void updateBookingTest() {
        String bookingJson = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 555,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-01-01\",\n" +
                "        \"checkout\" : \"2025-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        APIResponse apiPUTResponse = requestContext.put("https://restful-booker.herokuapp.com/booking/1", RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setHeader("Cookie", "token=" + TOKEN_ID)
                .setData(bookingJson));

        System.out.println(apiPUTResponse.url());
        System.out.println(apiPUTResponse.status() + " : " + apiPUTResponse.statusText());
        Assert.assertEquals(apiPUTResponse.status(), 200);
        System.out.println(apiPUTResponse.text());
    }

    @Test
    public void deleteBookingTest() {
        APIResponse apiDeleteResponse = requestContext.delete("https://restful-booker.herokuapp.com/booking/1", RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setHeader("Cookie", "token=" + TOKEN_ID));

        System.out.println(apiDeleteResponse.status() + " : " + apiDeleteResponse.statusText());
        System.out.println(apiDeleteResponse.url());
        System.out.println(apiDeleteResponse.text());
        Assert.assertEquals(apiDeleteResponse.status(), 201);
        Assert.assertTrue(apiDeleteResponse.text().contains("Created"));
    }
}