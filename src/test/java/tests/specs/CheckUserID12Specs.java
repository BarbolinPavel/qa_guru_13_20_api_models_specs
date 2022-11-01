package tests.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.notNullValue;
import static tests.helpers.AllureRestAssuredFilter.withCustomTemplates;

public class CheckUserID12Specs {
    public static RequestSpecification checkUserID12RequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body();
    public static ResponseSpecification checkUserID12ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody("data.findAll {id = 12}.first_name", notNullValue())
            .build();
}