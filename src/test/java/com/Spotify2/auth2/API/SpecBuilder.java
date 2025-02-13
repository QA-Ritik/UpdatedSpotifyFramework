package com.Spotify2.auth2.API;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.Spotify2.auth2.API.RoutesForURL.BASE_PATH;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){

        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("BASE_URI")).
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).build();

    }

    public static RequestSpecification getAccountPostRequestSpec(){

        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("ACCOUNT_BASE_URI")).
                setContentType(ContentType.URLENC).
                addHeader("Authorization","Basic MDVjOTczZGE4MDgyNDk3NzlmY2I0NzM0ODIyYWMwYzM6YjcxODBjYTQ3MGY5NDk4MmI4MzhlNDkwOTZjZDA5OGE=").
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).build();
    }

    public static ResponseSpecification getResponseSpec(){
        return  new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}
