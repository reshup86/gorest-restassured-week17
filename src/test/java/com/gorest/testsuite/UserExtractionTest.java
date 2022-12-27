package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    //1. Extract All Ids
    @Test
    public void test001(){
        List<?> id =  response.extract().path("id");
        System.out.println("Extract All Ids : " + id);
    }
    //2. Extract the all Names
    @Test
    public void test002(){
        List<String> name = response.extract().path("name");
        System.out.println("Extract the all Names : "+name);
    }
    //3. Extract the name of 5th object
    @Test
    public void test003(){
        String getName = response.extract().path("name[4]");
        System.out.println("Extract the name of 5th object : "+getName);
    }
    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004(){
        List<String> allNames = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("Extract the names of all object whose status = inactive : " +allNames);
    }
    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005(){
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("Extract the gender of all the object whose status = active : " +gender);
    }
    //6. Print the names of the object whose gender = female
    @Test
    public void test006(){
        List<String> name = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("Name of Female : " +name);
    }
    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007(){
        List<String> allEmails = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("Get all the emails of the object where status = inactive : " +allEmails);
    }
    //8. Get the ids of the object where gender = male
    @Test
    public void test008(){
        List<?> ids = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("Get the ids of the object where gender = male : "+ids);
    }
    //9. Get all the status
    @Test
    public void test009(){
        List<String> status = response.extract().path("status");
        System.out.println("Get all the status : "+status);
    }
    //10. Get email of the object where name = Karthik Dubashi IV
    @Test
    public void test010(){
        String email = response.extract().path("find{it.name == 'Raj Patil'}.email");
        System.out.println("Get email of the object where name is Raj Patil is :"+email);
    }
    //11. Get gender of id = 5471
    @Test
    public void test011(){
        String gender = response.extract().path("find{it.id == 5309}.gender");
        System.out.println("Get gender of id is 5309 is :"+gender);
    }
}
