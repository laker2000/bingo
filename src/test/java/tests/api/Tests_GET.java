package tests.api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Tests_GET {

    @Test
    public void test_1(){

        given().
            get("http://127.0.0.1:55555/api/version")
        .then().
            statusCode(200).
            body("name", equalTo("request-baskets")).
            body("source_code", equalTo("https://github.com/darklynx/request-baskets"));
    }

}
