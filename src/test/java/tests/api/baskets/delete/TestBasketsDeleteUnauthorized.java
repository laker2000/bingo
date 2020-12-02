package tests.api.baskets.delete;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import tests.TestConfig;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestBasketsDeleteUnauthorized extends TestConfig {

    /**
     * Scenario: Check deletion without any authorization
     *
     * Test steps:
     *
     * 1. Create a basket
     * 2. Assert that basket is created
     * 3. Delete a given basket without auth
     * 4. Assert that 401 code is received
     *
     * */
    @Test
    public void testBasketsDeleteUnauthorized(){
        // 1. Create a basket
        RequestSpecBuilder builder = new RequestSpecBuilder();
        String basketName = RandomStringUtils.randomAlphanumeric(10);
        builder.addPathParam("name", basketName);
        builder.addHeader("Authorization", serviceToken);

        HashMap<String, Object> basketContent = new HashMap<>();
        basketContent.put("forward_url", "https://nba.com");
        basketContent.put("proxy_response", true);
        basketContent.put("insecure_tls", true);
        basketContent.put("expand_path", true);
        basketContent.put("capacity", 321);

        var requestSpec = builder.build();
        var request = RestAssured.given().spec(requestSpec);

        request.body(basketContent);
        var postResponse = request.post(basketByName);
        assertThat(postResponse.statusCode(), is(201));
        String basket_token = "";

        // 2. Assert that basket is created
        builder.addPathParam("name", basketName).build();
        postResponse = request.get(basketByName);
        assertThat(postResponse.statusCode(), is(200));
        assertThat(postResponse.getBody().jsonPath().get("forward_url"), is("https://nba.com"));

        // 3. Delete a given basket without auth
        builder = new RequestSpecBuilder();
        builder.addPathParam("name", basketName);
        requestSpec = builder.build();
        request = RestAssured.given().spec(requestSpec);

        postResponse = request.delete(basketByName);
        assertThat(postResponse.statusCode(), is(401));

    }
}
