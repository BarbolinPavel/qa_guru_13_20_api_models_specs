package tests.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.notNullValue;
import static tests.helpers.AllureRestAssuredFilter.withCustomTemplates;

public class CheckUser15Specs {
    public static RequestSpecification checkUser15RequestSpec = with()
            .basePath("/users/15")
            .filter(withCustomTemplates())
            .log().uri()
            .log().body();
    public static ResponseSpecification checkUser15ResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(404)
            .build();
}