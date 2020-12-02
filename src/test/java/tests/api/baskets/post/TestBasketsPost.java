package tests.api.baskets.post;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import tests.TestConfig;

public class TestBasketsPost extends TestConfig {

    @Test
    public void testBasketPost(){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(RestAssured.baseURI);
        builder.setContentType(ContentType.JSON);
        String basketName = "mark3o";
        builder.addPathParam("name", basketName);
        builder.addHeader("Authorization", serviceToken);
        var requestSpec = builder.build();
        var request = RestAssured.given().spec(requestSpec);

        var postResponse = request.post(basketByName);
        System.out.println();

    }

    //todo: test kada dva put kreiras basket sa istim imenom, proeris 409 kod, ali i proveri
    //todo za sv slucaj da li se na UI prikazuje duplikat

}
