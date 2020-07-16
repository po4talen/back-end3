package step.definition;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static com.brainacad.ReqSpec.REQUEST_SPEC;
import static io.restassured.RestAssured.given;

public class Steps {

    private String url;
    private ValidatableResponse response;
    private ValidatableResponse response2;

    @Given("I have server by url {string}")
    public void i_have_server_by_url(String url) {
        this.url = url;
        //    throw new io.cucumber.java.PendingException();
    }

    @When("I send GET request on endpoint {string} and page {int}")
    public void i_send_GET_request_on_endpoint_and_parameters(String endpoint, int pageNumber) {

        response = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .basePath(endpoint)
                .param("page", pageNumber)
                .when()
                .get()
                .then();
        //   throw new io.cucumber.java.PendingException();
    }

    @Then("I get response status code {int}")
    public void i_get_response_status_code(Integer code) {
        response.statusCode(code);
    }


    @When("I send POST request on endpoint {string}")
    public void I_send_POST_request_on_endpoint(String endpoint) {
        response2 = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .basePath(endpoint)
                .when()
                //.body(user) //создание реквеста через lombok
                .body("{\"name\": \"morpheus\", \"job\": \"leader\" }")
                .post()
                .then();
    }

    @Then("I get response status code2 {int}")
    public void get_response_status_code(Integer code) {
        response2.statusCode(code);
    }

//    @Then ("I check name {login} and job {job}")
//
//    public void get_responce_body (String login, String job){
//        response2.body();
//    }
//


//    @when ("^ I login as (User|admin)$")
//    .*@acad.com
}