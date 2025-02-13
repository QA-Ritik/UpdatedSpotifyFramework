package com.Spotify2.auth2.API.ApplicationAPIs;

import com.Spotify2.auth2.API.RestResources;
import com.Spotify2.auth2.Utils.ConfigLoader;
import com.Spotify2.auth2.pojo.Playlist;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.Spotify2.auth2.API.RoutesForURL.PLAYLISTS;
import static com.Spotify2.auth2.API.RoutesForURL.USERS;
import static com.Spotify2.auth2.API.TokenManager.getToken;



public class PlaylistAPIs {

    @Step
    public static Response post(Playlist requestPlaylist){

        return RestResources.post(USERS+"/"+ ConfigLoader.getInstance().getUserId()+PLAYLISTS, requestPlaylist, getToken());

    }
    public static Response get(String playlistID){

        return RestResources.get(PLAYLISTS+"/"+playlistID, getToken());

    }
    public static Response get(String playlistID, String token){

        return RestResources.get(PLAYLISTS+"/"+playlistID, token);

    }

    public static Response update(Playlist requestPlaylist, String playlistID){
        return RestResources.update(requestPlaylist,PLAYLISTS+"/"+playlistID, getToken());

    }
}
