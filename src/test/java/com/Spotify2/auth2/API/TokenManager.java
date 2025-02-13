package com.Spotify2.auth2.API;

import com.Spotify2.auth2.Utils.ConfigLoader;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public static String getToken(){

        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing the new token...");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSec = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSec -300);
            }
            else{
                System.out.println("Available token is good to use... ");
            }

        }
        catch (Exception e){
            throw new RuntimeException("Token renovation failed... Check the logs..");
        }

        return access_token;
    }

    private static Response renewToken(){

        HashMap<String,String> formParameters = new HashMap<String, String>();
        formParameters.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParameters.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        formParameters.put("client_id", ConfigLoader.getInstance().getClientID());

       Response response= RestResources.accountPost(formParameters);

       if(response.statusCode() != 200){
           throw new RuntimeException("We are not able to renew the token. Please check the logs.");
       }
       else {
           return response;
       }

    }
}
