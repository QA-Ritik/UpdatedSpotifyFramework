package com.Spotify2.auth2.API;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.Spotify2.auth2.API.RoutesForURL.API;
import static com.Spotify2.auth2.API.RoutesForURL.TOKEN;
import static com.Spotify2.auth2.API.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResources {

    public static Response post(String path, Object requestPlaylist, String token){

        return given(getRequestSpec()).body(requestPlaylist).
                auth().oauth2(token).
                when().post(path)
                .then().spec(getResponseSpec()).
                extract().response();
    }
    public static Response get(String path,String token ){

       return given(getRequestSpec()).
               auth().oauth2(token).
                when().get(path)
                .then().
               spec(getResponseSpec()).
               extract().response();

    }

    public static Response update(Object requestPlaylist, String path, String token){

       return given(getRequestSpec()).body(requestPlaylist).
               auth().oauth2(token).
                when().put(path)
                .then().
                spec(getResponseSpec()).extract().response();

    }
    public static Response accountPost(HashMap<String, String> formParameters){
        return
                given(getAccountPostRequestSpec()).
                        formParams(formParameters).
                        when().
                        post(API+TOKEN).
                        then().
                        spec(getResponseSpec()).
                        extract().response();
    }
}
