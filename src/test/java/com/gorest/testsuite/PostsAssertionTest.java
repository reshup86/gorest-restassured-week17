package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
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

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response = given()
                .when()
                .get("/users?page=1&per_page=25")
                .then().statusCode(200);
        List<Integer> total = response.extract().path("id");
        Assert.assertEquals(total.size(), 25);
    }

    //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum.”
    @Test
    public void test002() {
        response.body("[2].title",equalTo("Ad ipsa coruscus ipsam eos demitto centum."));
    }
    //3. Check the single user_id in the Array list (5522)
    @Test
    public void test003() {
        response.body("user_id", hasItem(5522));
    }
    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("id",hasItems(2737,2731,2730));
    }
    //5.
    @Test
    public void test005() {
        response.body("[1].body",equalTo("Totidem desolo tabula. Confero tabula laborum. Sed confugo cilicium. Cur tantillus et. Suadeo defessus distinctio. Perferendis speciosus somniculosus. Id trado maiores. Tunc aeternus charisma. Cauda territo defluo. Thymum amplus ustilo. Decet caecus concido. Canonicus aeneus cito. Vulgivagus stultus non. Et confero corporis. Cribro ducimus thesaurus. Amplexus vomer aliquam. Laudantium amplexus debeo. Celebrer unde ademptio. Unus vulnero suscipit. Antea approbo tollo. Curto iusto ultio."));
    }
}
