package tests.api.baskets.post;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import tests.TestConfig;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestBasketsPostNameDuplication extends TestConfig {

    /**
     * Scenario: Check basket duplication
     *
     * Test steps:
     *
     * 1. Arrange a basket for creation
     * 2. Create a given basket
     * 3. Assert that basket is created
     * 4. Try to create the same basket again
     * 5. Assert that 409 code is received, Assert response message
     *
     * */
    @Test
    public void testBasketsPostNameDuplication(){
        // 1. Arrange a basket for creation
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

        // 2. Create a given basket
        var requestSpec = builder.build();
        var request = RestAssured.given().spec(requestSpec);
        request.body(basketContent);

        var postResponse = request.post(basketByName);
        assertThat(postResponse.statusCode(), is(201));

        // 3. Assert that basket is created
        postResponse = request.get(basketByName);
        assertThat(postResponse.statusCode(), is(200));
        assertThat(postResponse.getBody().jsonPath().get("forward_url"), is("https://nba.com"));
        assertThat(postResponse.getBody().jsonPath().get("proxy_response"), is(true));
        assertThat(postResponse.getBody().jsonPath().get("insecure_tls"), is(true));
        assertThat(postResponse.getBody().jsonPath().get("expand_path"), is(true));
        assertThat(postResponse.getBody().jsonPath().get("capacity"), is(321));

        // 5. Assert that 409 code is received, Assert response message
        String expectedMessage = String.format("Basket with name '%s' already exists\n", basketName);
        postResponse = request.post(basketByName);
        assertThat(postResponse.statusCode(), is(409));
        assertThat(postResponse.body().asString(), is(expectedMessage));

    }

}
