package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
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

    //1. Verify the if the total record is 20
    @Test
    public void test001(){
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
        List<Integer> total = response.extract().path("id");
        Assert.assertEquals(total.size(), 20);
    }
    //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void test002(){
        response.body("[1].name",equalTo("Subodh Menon"));
    }
    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003(){
        response.body("name", hasItem("Kashyap Prajapat"));
    }
    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    @Test
    public void test004(){
        response.body("name",hasItems("Subodh Menon","Kashyap Prajapat","Meghnad Jain","Udit Menon"));
    }
    //5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005(){
        response.body("[3].email",equalTo("meghnad_jain@schaefer.io"));
    }
    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006(){
        response.body("[1].status",equalTo("active"));
    }
    //7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test007(){
        response.body("[3].gender",equalTo("male"));
    }

}
