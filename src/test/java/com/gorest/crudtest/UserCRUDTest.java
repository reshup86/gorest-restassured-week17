package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    //verifyUserCreatedSuccessfully()
    @Test
    public void verifyUserCreatedSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Aniket Mathur");
        userPojo.setEmail(getRandomValue()+"aniketma2@example.com");
        userPojo.setGender("Male");
        userPojo.setStatus("Active");
        Response response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                        .body(userPojo)
                        .when()
                        .post("/users");
        response.then().statusCode(201);
        response.prettyPrint();
    }

    //verifyUserUpdateSuccessfully()
    @Test
    public void verifyUserUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Aniket Mathur");
        userPojo.setEmail(getRandomValue()+"aniketmathur12@example.com");
        userPojo.setGender("Male");
        userPojo.setStatus("Active");
        Response response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                        .body(userPojo)
                        .when()
                        .patch("/users/11427");
                        //.post("/users");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //VerifyUserDeleteSuccessfully()
    @Test
    public void verifyUserDeleteSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Aniket Mathur");
        userPojo.setEmail(getRandomValue()+"aniketmathur12@example.com");
        userPojo.setGender("Male");
        userPojo.setStatus("Active");
        Response response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                        .body(userPojo)
                        .when()
                        .delete("/users/11427");
        //.post("/users");
        response.then().statusCode(204);
        response.prettyPrint();
    }
}
