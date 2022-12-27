package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Extract the title
    @Test
    public void test001() {
        List<?> title = response.extract().path("title");
        System.out.println("Total records are : " + title);
    }

    //2. Extract the total number of record
    @Test
    public void test002() {
        List<String> record = response.extract().path(("id"));
        int totalRecord = record.size();
        System.out.println("The totalnumber of record is : " + totalRecord);
    }

    //3. Extract the body of 15th record
    @Test
    public void test003() {
        response = given()
                .when()
                .get("/posts?page=1&per_page=24")
                .then().statusCode(200);
        List<String> recordBody = response.extract().path("findAll{it.id == 2645}.body");
        System.out.println("The totalnumber of record is : " + recordBody);
    }

    //4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<?> userIds = response.extract().path(("user_id"));
        System.out.println("User_id of all records are: " + userIds);
    }

    //5. Extract the title of all the records
    @Test
    public void test005() {
        List<String> title = response.extract().path(("title"));
        System.out.println("Extract the title of all the records " + title);
    }

    //6. Extract the title of all records whose user_id = 5456
    @Test
    public void test006() {
        List<String> title = response.extract().path(("findAll{it.user_id == 5559}.title"));
        System.out.println("Extract the title of all the records " + title);
    }

    //7. Extract the body of all records whose id = 2671
    @Test
    public void test007() {
        List<String> body = response.extract().path(("findAll{it.user_id == 5559}.body"));
        System.out.println("Extract the title of all the records " + body);
    }
}
