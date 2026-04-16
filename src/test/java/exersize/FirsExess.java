package exersize;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirsExess {
    @Test
    public void api() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";

        Response book = RestAssured
                .given() //
                .header("accept", "application/json")
                .when()  //get,post, put, endpoint
                .get("BookStore/v1/Books")
                .then()
                .log().all()   //dalogos da responsi amoaeqstratos
                .extract().response();

        int statuscode = book.statusCode();
        Assert.assertEquals(statuscode, 200, "sworia");

        String author = book.jsonPath().getString("books[0].author");
        String publisher = book.jsonPath().getString("books[0].publisher");
        Assert.assertEquals(author, "Richard E. Silverman");
        Assert.assertEquals(publisher, "O'Reilly Media");

        System.out.println(author);

    }


    @Test
    public void api1() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        JSONObject requesBody = new JSONObject();
        requesBody.put("userName", "tsira3311") //უზერის ცვლილება უნდა ყოველ ჯერზე
                .put("password", "Gazafxuli@2026");

        Response user = RestAssured
                .given()
                .contentType("application/json")
                .body(requesBody.toString())
                .when()
                .post("Account/v1/User")
                .then()
                //.log().all()   //dalogos da responsi amoaeqstratos
                .extract().response();


        int statuscode = user.statusCode();
        Assert.assertEquals(statuscode, 201, "sworia");
        System.out.println("სტატუსკოდი" + ' ' + statuscode);
        String responsbody = user.getBody().asString();
        System.out.println(responsbody);
        Assert.assertTrue(responsbody.contains("userID"), "true");
    }

    @Test
    public void api2() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        JSONObject requesBody = new JSONObject();
        requesBody.put("userName", "tsira51")
                .put("password", "gazafxuli");

        Response user = RestAssured
                .given()
                .contentType("application/json")
                .body(requesBody.toString())
                .when()
                .post("Account/v1/User")
                .then()
                //.log().all()   //dalogos da responsi amoaeqstratos
                .extract().response();
        int statuscode = user.statusCode();
        Assert.assertEquals(statuscode, 400, "sworia");
        String responsbody = user.getBody().asString();
        Assert.assertTrue(responsbody.contains("message"), "true");
        System.out.println(responsbody);
        System.out.println(statuscode);


    }
}