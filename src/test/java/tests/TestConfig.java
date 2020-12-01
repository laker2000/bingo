package tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

import java.io.*;
import java.io.IOException;


public class TestConfig {

    protected static String serviceToken;
    protected static String version;
    protected static String stats;
    protected static String baskets;
    protected static String responses;
    protected static String requests;

    protected static String test_config_file;


    @BeforeTest
    public void setup() throws IOException {

        version = "/version";
        baskets = "/baskets";
        stats = "/stats";
        responses = "/responses";
        requests = "/requests";
        test_config_file = "./test.config";

        set_env_config(test_config_file);

    }

    private void set_env_config(String test_config) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(test_config));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String curLine,  baseUri = null;
        String[] split;

        while ((curLine = bufferedReader.readLine()) != null){

            if (curLine.contains("generated master token")){
                split = curLine.split(" ");
                serviceToken = split[split.length - 1];
            }

            if (curLine.contains("HTTP server is listening on")){
                split = curLine.split(" ");
                baseUri = split[split.length - 1];
            }

        }
        bufferedReader.close();

        if (serviceToken == null || baseUri == null) {
            throw new IOException("Failed to get environment configuration.");
        }

        RestAssured.baseURI = "http://"+baseUri.trim().concat("/api");
    }

}
