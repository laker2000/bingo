
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;




public class Test01_GET {
    @Test
    void test_01(){

        Response response = get("http://127.0.0.1:55555/api/version");

        given()
            .get("http://127.0.0.1:55555/api/version")
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
            .get("http://128.0.0.1:55555/api/version")
        .then()
            .statusCode(200)
            .body("source_code", equalTo("https://github.com/darklynx/request-baskets"));


    }

}
