package tests.api.baskets.delete;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import tests.TestConfig;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestBasketsDeleteWrongName extends TestConfig {

    /**
     * Scenario: Check deletion of a basket without desired basket name in the path
     *
     * Test steps:
     *
     * 1. Create a basket
     * 2. Assert that basket is created
     * 3. Try to delete a given basket
     * 4. Assert that 400 code is received
     *
     * */
    @Test
    public void testBasketsDeleteWrongName(){
        // Arrange
        String basketName = RandomStringUtils.randomAlphanumeric(10);
        String wrongBasketName = "I do not exist";

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addPathParam("name", basketName);
        builder.addHeader("Authorization", serviceToken);
        HashMap<String, Object> basketContent = new HashMap<>();
        basketContent.put("capacity", 321);

        // 1. Create a basket
        var requestSpec = builder.build();
        var request = RestAssured.given().spec(requestSpec);
        request.body(basketContent);
        var postResponse = request.post(basketByName);

        assertThat(postResponse.statusCode(), is(201));

        // 2. Assert that basket is created
        postResponse = request.get(basketByName);
        assertThat(postResponse.statusCode(), is(200));
        assertThat(postResponse.getBody().jsonPath().get("capacity"), is(321));

        // 3. Try to delete a given basket
        builder = new RequestSpecBuilder();
        builder.addHeader("Authorization", serviceToken);
        builder.addPathParam("name", wrongBasketName);
        requestSpec = builder.build();
        request = RestAssured.given().spec(requestSpec);
        postResponse = request.delete(basketByName);

        // 4. Assert that 400 code is received
        assertThat(postResponse.statusCode(), is(400));

    }
}
