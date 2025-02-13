package com.Spotify2.auth2.tests;

import com.Spotify2.auth2.API.ApplicationAPIs.PlaylistAPIs;
import com.Spotify2.auth2.API.StatusCode;
import com.Spotify2.auth2.Utils.DataLoader;
import com.Spotify2.auth2.pojo.Error;
import com.Spotify2.auth2.pojo.Playlist;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.Spotify2.auth2.Utils.FakerUtils.generateDescription;
import static com.Spotify2.auth2.Utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Epic("Spotify Playlist collection API")
public class PlayListTests extends ThreadPrinter {

    @Story("Create a new list")
    @Description("Test description...")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    @Test(description = "New playlist should be created without any issue.")
    public void shouldAbleToCreatePlayList(){

         Playlist requestPlaylist = playlistRequestBuilder(generateName(),
                 generateDescription(), false);

         Response response = PlaylistAPIs.post(requestPlaylist);
         playlistAssertEqual(response.as(Playlist.class), requestPlaylist);
         statusChecker(response.statusCode(), StatusCode.CODE_201.getCode());
     }

     @Story("Get the playlist")
     @Test(description = "Playlist should be fetched without any issue.")
    public void shouldAbleToGetPlayList(){

         Response response = PlaylistAPIs.get(DataLoader.getInstance().playlistIDforGET());

         statusChecker(response.statusCode(), StatusCode.CODE_200.getCode());
         assertForGET(response.as(Playlist.class),"PLAYLIST For GET Request","This is for testing the GET API calls.", true);
    }

    @Story(" Update the Playlist")
    @Test (description = "Playlist should be updated without any issue.")
    public void shouldAbleToUpdatePlayList(){

        Playlist requestPlaylist = playlistRequestBuilder(
                generateName(),
                generateDescription(), false);

       Response response = PlaylistAPIs.update(requestPlaylist, DataLoader.getInstance().playlistIDforUPDATE());
        statusChecker(response.statusCode(), StatusCode.CODE_200.getCode());
    }

    @Story("Negative Test for Playlist")
    @Test (description = "New playlist should NOT be created without any issue.")
    public void shouldNotBeAbleToCreatePlayList(){

        Playlist requestPlaylist = playlistRequestBuilder("",
                generateDescription(), false);

       Response response = PlaylistAPIs.post(requestPlaylist);

        statusChecker(response.statusCode(), StatusCode.CODE_400.getCode());
        assertError(response.as(Error.class), StatusCode.CODE_400.getCode(), StatusCode.CODE_400.getMessage());

    }

    @Story("Expired Token")
    @Test (description = "Call should return Expired token error.")
    public void shouldNotBeAbleToGetPlayListWithExpiredToken(){

         Response response = PlaylistAPIs.get(DataLoader.getInstance().playlistIDforGET(), "123");

        statusChecker(response.statusCode(), StatusCode.CODE_401.getCode());
        assertError(response.as(Error.class),StatusCode.CODE_401.getCode(), StatusCode.CODE_401.getMessage() );
     }



     @Step
    public Playlist playlistRequestBuilder(String name, String description, boolean _public){
        return Playlist.builder().
                name(name).
                description(description).
                _public(_public).
                build();
    }


    public void playlistAssertEqual(Playlist responsePlaylist, Playlist requestPlaylist ){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }


    public void statusChecker(int actualCode, int expectedCode){
        assertThat(actualCode, equalTo(expectedCode));
    }


    public void assertError(Error responseError, int expectedStatus, String expectedMsg){
        assertThat(responseError.getError().getStatus(), equalTo(expectedStatus));
        assertThat(responseError.getError().getMessage(), equalTo(expectedMsg));
    }


    public void assertForGET(Playlist responseError, String expectedName, String expectedDescription, boolean expectedPublic){
        assertThat(responseError.getName(), equalTo(expectedName));
        assertThat(responseError.getDescription(), equalTo(expectedDescription));
        assertThat(responseError.get_public(), equalTo(expectedPublic));
    }

}
