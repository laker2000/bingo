
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;




public class Test01_GET {
    @Test
    void test_01(){

        Response response = get("https://rbaskets.in/api/version");

        given()
            .get("https://rbaskets.in/api/version")
        .then()
            .body("name", equalTo("request-baskets"));

        String responseString = response.asString();

        int responseStatusCode = response.statusCode();


        long responseTime = response.getTime();


        Assert.assertEquals(responseStatusCode, 200);


    }
    @Test()
    void Test02_GET(){
        //bolja metoda jer stavlja minimalan i konkretan errorTrace
        given()
            .get("https://rbaskets.in/api/version")
        .then()
            .statusCode(200)
            .body("source_code", equalTo("https://github.com/darklynx/request-baskets"));


    }

}
