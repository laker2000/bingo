package tests.api.baskets;

import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import tests.TestConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestBaskets extends TestConfig {

    // note: pada posle prvog run-a, jer se stanje menja..resi to
//    @Test()
//    public void testBasketsGetStatsEmptyState()
//    {
//        given()
//            .headers("Authorization", serviceToken)
//            .get(stats)
//        .then()
//            .statusCode(200)
//        .and()
//            .body("baskets_count", equalTo(0))
//            .body("empty_baskets_count", equalTo(0))
//            .body("requests_count", equalTo(0))
//            .body("requests_total_count", equalTo(0))
//            .body("max_basket_size", equalTo(0))
//            .body("avg_basket_size", equalTo(0))
//            .body("top_baskets_size", equalTo(null))
//            .body("top_baskets_recent", equalTo(null))
//        .and()
//            .log()
//            .all();
//
//    }

    @Test()
    public void testBasketsPost()
    {
       ref_basket_name = RandomStringUtils.randomAlphanumeric(10);

       given()
           .contentType(ContentType.JSON)
           .headers("Authorization", serviceToken)
           .pathParam("name", ref_basket_name)
       .when()
           .post(basketByName)
       .then()
           .statusCode(201);

    }
}
