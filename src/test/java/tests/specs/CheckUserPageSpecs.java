package tests.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.notNullValue;
import static tests.helpers.AllureRestAssuredFilter.withCustomTemplates;

public class CheckUserPageSpecs {
    public static RequestSpecification checkUserPageRequestSpec = with()
            .basePath("/users?page=2")
            .filter(withCustomTemplates())
            .log().uri()
            .log().body();
    public static ResponseSpecification checkUserPageResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody("total_pages", notNullValue())
            .build();
}
