package exersize;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Data;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SecondTask {
    @DataProvider(name = "userIsbn")
    public Object[][] getData() {
        return new Object[][]{
                {"9781449331818"},
                {"9781449337711"},
                {"9781449365035"},
                {"9781491904244"},
        };
    }

    @Test(dataProvider = "userIsbn")
    public void testGetRequest(String userIsbn) {
        RestAssured.baseURI = "https://bookstore.toolsqa.com/";
        Response response = RestAssured
                .given()
                .param("ISBN", userIsbn)
                .when()
                .get("/BookStore/v1/Book")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(), 200, "status code 200");
        String isbn = response.jsonPath().getString("isbn");
        Assert.assertEquals(isbn, userIsbn);

    }

    @Test
    public void Userobject() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com/";
        User newUser = new User();
        newUser.setUserName("tsiraMss42s");
        newUser.setPassword("Mesablishvili*11226");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .post("/Account/v1/User")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(), 201, "statuscode should be 201");
        String responsbody = response.getBody().asString();
        System.out.println(responsbody);
        Assert.assertTrue(responsbody.contains("userID"), "contains userid");

    }

    @Test
    public void Userobject1() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com/";
        User newUser = new User();
        newUser.setUserName("tsira");
        newUser.setPassword("Mesablishv");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .post("/Account/v1/User")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(), 400, "statuscode should be 400");
        String responsbody = response.getBody().asString();
        Assert.assertTrue(responsbody.contains("message"), "„Passwords is not correct");


    }
}
