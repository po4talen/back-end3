package com.brainacad;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static com.brainacad.ReqSpec.REQUEST_SPEC;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;



public class RestAssuredTest {
/*     public class BodyToAllureTest {
         RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
           //     .addHeaders("ContentType", "application/json")
                .setContentType(ContentType.JSON)
                .setBasePath("/api/users")
            //  .build();

    }
    */

    @Test //GET LIST USERS
    public void CheckGetResponseListUsers() {
        given()
                .spec(REQUEST_SPEC)
                .pathParam("page", "users")
                .pathParam("id", "")
                .param("page",2)
                .log().body()
                .when()
                .get()
                .then()
                .log().body()
                .statusCode(200)
                .and()
               // .body("data",hasEntry("id",12))
                .body("$", hasKey("data"))
                .body("data[0]", hasKey("first_name"))
                .body("data[0].first_name", equalTo("Michael"))
                .body(matchesJsonSchemaInClasspath("usersResponseSchema.json"));
    }

    @Test //GET SINGLE USER
    public void CheckGetResponseSingleUser(){
        given()
                .spec(REQUEST_SPEC)
                .pathParam("page", "users")
                .pathParam("id", "2")
                .log().body()
                .when()
                .get()
                .then()
                .log().body()
                .statusCode(200)
                .and()
                .body("$", hasKey("data"))
                .body("data", hasKey("id"))
                .body("data", hasEntry("id",2))
                .body("data",hasEntry("first_name", "Janet"));
    }

    @Test //GET SINGLE USER NOT FOUND
    public void CheckGetSingleUserNotFound(){
        given()
                .spec(REQUEST_SPEC)
                .pathParam("page", "users")
                .pathParam("id", "23")
                .log().body()
                .when()
                .get()
                .then()
                .log().body()
                .statusCode(404);
    }

    @Test //GET LIST <RESOURCE>
    public void CheckGetListResource(){
        ValidatableResponse resp =
        given()
                .spec(REQUEST_SPEC)
                .pathParam("page", "unknown")
                .pathParam("id", "")
                .log().body()
                .when()
                .get()
                .then()
                .log().body()
                .statusCode(200);
                resp.body("data.size()", equalTo(resp.extract().body().path("per_page")))
                 .body(matchesJsonSchemaInClasspath("unknownResponseSchema.json"));

    }

    @Test // GET SINGLE <RESOURCE>
    public void CheckGetSingleResource(){
        given()
                .spec(REQUEST_SPEC)
                .pathParam("page", "unknown")
                .pathParam("id", "2")
                .log().body()
                .when()
                .get()
                .then()
                .log().body()
                .statusCode(200)
                .body("ad.url", containsString("http://statuscode.org/"))
                .body("data.name", containsString("rose"));

    }

    @Test //GET <RESOURCE> NOT FOUND
    public void GetResourceNotFound() {
        given()
                .spec(REQUEST_SPEC)
                .pathParam("page", "unknown")
                .pathParam("id", "23")
                .log().body()
                .when()
                .get()
                .then()
                .log().body()
                .statusCode(404);
    }

    @Test // POST CREATE
    public void PostCreate(){

        Users user = Users.builder()
                .name("morpheus")
                .job("leader")
                .build();

        given()
                .spec(REQUEST_SPEC)
                .pathParam("page", "users")
                .pathParam("id", "")
                .log().body()
                .when()
                .body(user) //создание реквеста через lombok
               // .body("{\"name\": \"morpheus\", \"job\": \"leader\" }")
                .post()
                .then()
                .log().body()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));

    }

    @Test //PUT UPDATE
    public void PutUpdate(){
        given()
                .spec(REQUEST_SPEC)
                .pathParam("page", "users")
                .pathParam("id", "2")
                .log().body()
                .when()
                .body("{\"name\": \"morpheus\", \"job\": \"zion\" }")
                .put()
                .then()
                .log().body()
                .statusCode(200)
                .body("job", equalTo("zion"))
                .body("$", hasKey("updatedAt"))
                .extract()
                .body()
                .as(Nuser.class);

    }

    @Test // PATCH UPDATE
    public void PatchUpdate(){
        given()
                .spec(REQUEST_SPEC)
                .pathParam("page", "users")
                .pathParam("id", "2")
                .log().body()
                .when()
                .body("{\"name\": \"morpheus\", \"job\": \"zion p\" }")
                .patch()
                .then()
                .log().body()
                .statusCode(200)
                .body("job", equalTo("zion p"))
                .body("$", hasKey("updatedAt"));
    }

    @Test //DELETE DELETE
    public void Delete(){
        given()
                .spec(REQUEST_SPEC)
                .pathParam("page", "users")
                .pathParam("id", "2")
                .log().body()
                .when()
                .delete()
                .then()
                .log().body()
                .statusCode(204);
    }

    @Test // POST REGISTER - SUCCSESSFUL
    public void PostRegisterSuccsessul(){
        given()
                .spec(REQUEST_SPEC)
                .pathParam("page", "register")
                .pathParam("id", "")
                .log().body()
                .when()
                .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}")
                .post()
                .then()
                .log().body()
                .statusCode(200)
                .body("$", hasKey("id"))
                .body("$", hasKey("token"));
    }

   @Test // POST REGISTER - UNSUCCESSFUL
    public void PostRegisterUnseccsessful(){
        given()
                .spec(REQUEST_SPEC)
                .pathParam("page", "register")
                .pathParam("id", "")
                .log().body()
                .when()
                .body("{\"email\": \"eve.holt@reqres.in\"}")
                .post()
                .then()
                .log().body()
                .statusCode(400)
                .body("$", hasKey("error"));
   }

   @Test //POST LOGIN - SUCCESSFUL
    public void PostLoginSuccessful(){
       given()
               .spec(REQUEST_SPEC)
               .pathParam("page", "login")
               .pathParam("id", "")
               .log().body()
               .when()
               .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}")
               .post()
               .then()
               .log().body()
               .statusCode(200)
               .body("$", hasKey("token"));

   }

   @Test // POST LOGIN UNSUCCESSFUL
           public void  PostLoginUnseccessful() {
       given()
               .spec(REQUEST_SPEC)
               .pathParam("page", "login")
               .pathParam("id", "")
               .log().body()
               .when()
               .body("{\"email\": \"eve.holt@reqres.in\"}")
               .post()
               .then()
               .log().body()
               .statusCode(400)
               .body("$", hasKey("error"));
   }

   @Test // Get Delayed Response
    public void GetDelayedResponse(){
       given()
               .spec(REQUEST_SPEC)
               .pathParam("page", "login")
               .pathParam("id", "")
               .param("delay", 3)
               .log().body()
               .when()
               .get()
               .then()
               .log().body()
               .statusCode(200);


   }
}


