package tests.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;



public class Test_POST {

    @Test
    public void test_1_post(){
        String token = "HO8L6loQhJO5PgilOc6WAWbLzvK3UQ9VCpuqZHIAU7Uc";

        HashMap<String, String> map = new HashMap<>();
        map.put("Authorization", token);

        ObjectMapper mapper = new ObjectMapper();



        given().
            header("Content-Type", "application/json").
            contentType(ContentType.JSON).
            accept(ContentType.JSON).
            body(map). //a moze i samo map da se stavi ovaj gore
        when().
           post("http://127.0.0.1:55555/api/baskets/pera");





    }
}
