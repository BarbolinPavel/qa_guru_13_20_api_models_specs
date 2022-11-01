package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.models.pojo.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.specs.ChangeFirstNameSpecs.changeFirstNameRequestSpec;
import static tests.specs.ChangeFirstNameSpecs.changeFirstNameResponseSpec;
import static tests.specs.CheckUser15Specs.checkUser15RequestSpec;
import static tests.specs.CheckUser15Specs.checkUser15ResponseSpec;
import static tests.specs.CheckUserPageSpecs.checkUserPageResponseSpec;
import static tests.specs.LoginSpecs.loginRequestSpec;
import static tests.specs.LoginSpecs.loginResponseSpec;
import static tests.specs.CheckUserPageSpecs.checkUserPageRequestSpec;
import static tests.specs.CheckUserID12Specs.*;

public class APIPojoTest {
    @BeforeAll
    static void beforeAll(){

        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Tag("checkedApiPojo")
    @Test
    void checkUserPage() {
        CheckUserPageResponsePojoModel response =
                given()
                        .spec(checkUserPageRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(checkUserPageResponseSpec)
                        .extract()
                        .as(CheckUserPageResponsePojoModel.class);

        assertThat(response.getTotalPages()).isEqualTo("2");
    }

    @Tag("checkedApiPojo")
    @Test
    void checkUserID12() {
        given()
                .spec(checkUserID12RequestSpec)
                .get("/users?page=2")
                .then()
                .spec(checkUserID12ResponseSpec)
                .body("data.findAll {id = 12}.first_name", hasItems("Rachel"));
    }

    @Tag("checkedApiPojo")
    @Test
    void loginWithPojoModelTest() {
        LoginBodyPojoModel body = new LoginBodyPojoModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("proverka13");

        LoginResponsePojoModel response =
        given()
                .spec(loginRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(loginResponseSpec)
                .extract()
                .as(LoginResponsePojoModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }

    @Tag("checkedApiPojo")
    @Test
    void changeFirstNameTest() {
        ChangeFirstNameBodyPojoModel body = new ChangeFirstNameBodyPojoModel();
        body.setFirstName("morpheusi");

        ChangeFirstNameResponsePojoModel response =
        given()
                .spec(changeFirstNameRequestSpec)
                .body(body)
                .when()
                .put()
                .then()
                .spec(changeFirstNameResponseSpec)
                .extract()
                .as(ChangeFirstNameResponsePojoModel.class);

        assertEquals("morpheusi", response.getFirstName());
    }

    @Tag("checkedApiPojo")
    @Test
    void checkUser15() {
        given()
                .spec(checkUser15RequestSpec)
                .when()
                .get()
                .then()
                .spec(checkUser15ResponseSpec);
    }
}
