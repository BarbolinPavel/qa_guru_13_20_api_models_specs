package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.models.lombok.ChangeFirstNameBodyLombokModel;
import tests.models.lombok.CheckUserPageResponseLombokModel;
import tests.models.lombok.LoginBodyLombokModel;
import tests.models.lombok.LoginResponseLombokModel;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.specs.ChangeFirstNameSpecs.changeFirstNameRequestSpec;
import static tests.specs.ChangeFirstNameSpecs.changeFirstNameResponseSpec;
import static tests.specs.CheckUser15Specs.checkUser15RequestSpec;
import static tests.specs.CheckUser15Specs.checkUser15ResponseSpec;
import static tests.specs.CheckUserID12Specs.checkUserID12RequestSpec;
import static tests.specs.CheckUserID12Specs.checkUserID12ResponseSpec;
import static tests.specs.CheckUserPageSpecs.checkUserPageRequestSpec;
import static tests.specs.CheckUserPageSpecs.checkUserPageResponseSpec;
import static tests.specs.LoginSpecs.loginRequestSpec;
import static tests.specs.LoginSpecs.loginResponseSpec;

public class APILombokTest {
    @BeforeAll
    static void beforeAll(){

        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Tag("checkedApiLombok")
    @Test
    void checkUserPage() {
        CheckUserPageResponseLombokModel response =
                given()
                        .spec(checkUserPageRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(checkUserPageResponseSpec)
                        .extract()
                        .as(CheckUserPageResponseLombokModel.class);

        assertThat(response.getTotalPages()).isEqualTo(2);
    }

    @Tag("checkedApiLombok")
    @Test
    void checkUserID12() {
        given()
                .spec(checkUserID12RequestSpec)
                .get("/users?page=2")
                .then()
                .spec(checkUserID12ResponseSpec)
                .body("data.findAll {id = 12}.first_name", hasItems("Rachel"));
    }

    @Tag("checkedApiLombok")
    @Test
    void loginWithTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("proverka13");

        LoginResponseLombokModel response =
                given()
                        .spec(loginRequestSpec)
                        .body(body)
                        .when()
                        .post()
                        .then()
                        .spec(loginResponseSpec)
                        .extract()
                        .as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Tag("checkedApiLombok")
    @Test
    void changeFirstNameTest() {
        ChangeFirstNameBodyLombokModel body = new ChangeFirstNameBodyLombokModel();
        body.setFirstName("morpheus");

        ChangeFirstNameBodyLombokModel response =
                given()
                        .spec(changeFirstNameRequestSpec)
                        .body(body)
                        .when()
                        .put()
                        .then()
                        .spec(changeFirstNameResponseSpec)
                        .extract()
                        .as(ChangeFirstNameBodyLombokModel.class);

        assertEquals("morpheus", response.getFirstName());
    }

    @Tag("checkedApiLombok")
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
