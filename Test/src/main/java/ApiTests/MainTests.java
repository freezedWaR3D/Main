package ApiTests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MainTests {
    public final static String URL = "https://jsonplaceholder.typicode.com";

    @Test
    public void Create1() {
    UserData userData1 = given()
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 1,\n" +
                    "    \"title\": \"Rome\",\n" +
                    "    \"body\": \"Rome wasn’t built in a day\"\n" +
                    "  }")
            .when()
            .post(URL + "/posts")
            .then()
            .log().all()
            .assertThat()
            .statusCode(201)
            .body("userId", notNullValue())
            .body("id", notNullValue())
            .body("title", notNullValue())
            .body("body", notNullValue())
            .header("Content-Type", containsString("application/json"))
            .extract()
            .body()
            .as(UserData.class);
    }

    @Test                                                        //HTML ответ
    public void Create2() {
        UserData userData1 = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"userId\": ,\n" +
                        "    \"id\": 1,\n" +
                        "    \"title\": \"Where\",\n" +
                        "    \"body\": \"In vino veritas\"\n" +
                        "  }")
                .when()
                .post(URL + "/posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(500)
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue())
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .as(UserData.class);
    }

    @Test                                                        //HTML ответ
    public void Create3() {
        UserData userData1 = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"userId\": 1,\n" +
                        "    \"id\": ,\n" +
                        "    \"title\": \"Rome\",\n" +
                        "    \"body\": \"Rome wasn’t built in a day\"\n" +
                        "  }")
                .when()
                .post(URL + "/posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(500)
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue())
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .as(UserData.class);
    }

    @Test                                                        //HTML ответ
    public void Create4() {
        UserData userData1 = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"userId\": 1,\n" +
                        "    \"id\": 1,\n" +
                        "    \"title\": \"\",\n" +
                        "    \"body\": \"Rome wasn’t built in a day\"\n" +
                        "  }")
                .when()
                .post(URL + "/posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("userId", notNullValue())
                .body("id", notNullValue())
                .body("body", notNullValue())
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .as(UserData.class);
    }

    @Test                                                        //Баг
    public void Create5() {
        UserData userData1 = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"userId\": 1,\n" +
                        "    \"id\": 1,\n" +
                        "    \"title\": \"Rome\",\n" +
                        "    \"body\": \"\"\n" +
                        "  }")
                .when()
                .post(URL + "/posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("userId", notNullValue())
                .body("id", notNullValue())
                .body("title", notNullValue())
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .as(UserData.class);
    }

    @Test                                                        //HTML ответ
    public void Create6() {
        UserData userData1 = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"userId\": ,\n" +
                        "    \"id\": ,\n" +
                        "    \"title\": \"\",\n" +
                        "    \"body\": \"\"\n" +
                        "  }")
                .when()
                .post(URL + "/posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(500)
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .as(UserData.class);
    }









    @Test
    public void Read1(){
        List<UserData> users1= given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .get(URL + "/posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("userId", notNullValue())
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue())
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .jsonPath().getList("", UserData.class);
    }

    @Test
    public void Read2(){
        UserData userData2= given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .get(URL + "/posts/89")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("userId", notNullValue())
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue())
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .as(UserData.class);
    }

    @Test
    public void Read3(){
        UserData userData2= given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .get(URL + "/posts/101")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404)
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .as(UserData.class);
    }











    @Test
    public void Update1() {
        UserData userData1 = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"userId\": 1,\n" +
                        "    \"id\": 1,\n" +
                        "    \"title\": \"Where\",\n" +
                        "    \"body\": \"In vino veritas\"\n" +
                        "  }")
                .when()
                .put(URL + "/posts/1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("userId", notNullValue())
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue())
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .as(UserData.class);
    }


    @Test
    public void Update2() {
        UserData userData1 = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"userId\": 1,\n" +
                        "    \"id\": 1,\n" +
                        "    \"title\": \"\",\n" +
                        "    \"body\": \"\"\n" +
                        "  }")
                .when()
                .put(URL + "/posts/1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("userId", notNullValue())
                .body("id", notNullValue())
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .as(UserData.class);
    }


    @Test                                               //HTML ответ
    public void Update3() {
        List<String> user1 = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"userId\": 1,\n" +
                        "    \"id\": 1,\n" +
                        "    \"title\": \"\",\n" +
                        "    \"body\": \"\"\n" +
                        "  }")
                .when()
                .put(URL + "/posts/101")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404)
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .htmlPath().getList("", String.class);
    }


    @Test
    public void Update4() {
        List<String> users1 = given()
                .contentType(ContentType.JSON)
                .body("{\"title\": \"Where\",\n" +
                        "    \"body\": \"In vino veritas\"\n" +
                        "  }")
                .when()
                .patch(URL + "/posts/1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("userId", notNullValue())
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue())
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .htmlPath().getList("",String.class);
    }


    @Test                                                   //Баг
    public void Update5() {
        List<String> users1 = given()
                .contentType(ContentType.JSON)
                .body("{\"title\": \"Where\",\n" +
                        "    \"body\": \"In vino veritas\"\n" +
                        "  }")
                .when()
                .patch(URL + "/posts/105")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404)
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .htmlPath().getList("",String.class);
    }








    @Test
    public void Delete1() {
        UserData userData1 = given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .delete(URL + "/posts/1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .as(UserData.class);
    }


    @Test                                                   //Баг
    public void Delete2() {
        UserData userData1 = given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .delete(URL + "/posts/105")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404)
                .header("Content-Type", containsString("application/json"))
                .extract()
                .body()
                .as(UserData.class);
    }



}
