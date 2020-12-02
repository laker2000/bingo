package tests.api.baskets.delete;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import tests.TestConfig;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestBasketsDelete extends TestConfig {

    /**
     * Scenario: Check deletion of a basket
     *
     * Test steps:
     *
     * 1. Create a basket
     * 2. Assert that basket is created
     * 3. Delete a given basket
     * 4. Assert that given basket is deleted
     *
     * */
    @Test
    public void testBasketDelete(){
        // Arrange
        String basketName = RandomStringUtils.randomAlphanumeric(10);

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addPathParam("name", basketName);
        builder.addHeader("Authorization", serviceToken);

        HashMap<String, Object> basketContent = new HashMap<>();
        basketContent.put("forward_url", "https://nba.com");
        basketContent.put("proxy_response", true);
        basketContent.put("insecure_tls", true);
        basketContent.put("expand_path", true);
        basketContent.put("capacity", 321);

        // 1. Create a basket
        var requestSpec = builder.build();
        var request = RestAssured.given().spec(requestSpec);

        request.body(basketContent);
        var postResponse = request.post(basketByName);
        assertThat(postResponse.statusCode(), is(201));
        String basket_token = postResponse.getBody().jsonPath().get("token");

        // 2. Assert that basket is created
        builder.addPathParam("name", basketName); //todo proveri sta vraca ovde, fali build?
        postResponse = request.get(basketByName);
        assertThat(postResponse.statusCode(), is(200));
        assertThat(postResponse.getBody().jsonPath().get("capacity"), is(321));

        // 3. Delete a given basket
        builder.addHeader("Authorization", basket_token).build();
        postResponse = request.delete(basketByName);
        assertThat(postResponse.statusCode(), is(204));

        // 4. Assert that given basket is deleted
        postResponse = request.get(basketByName);

        assertThat(postResponse.statusCode(), is(404));

    }
}
