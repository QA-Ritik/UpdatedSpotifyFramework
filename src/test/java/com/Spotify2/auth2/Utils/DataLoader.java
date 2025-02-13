package com.Spotify2.auth2.Utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }
    public static DataLoader getInstance(){
        if (dataLoader == null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String playlistIDforGET(){
        String prop = properties.getProperty("get_playlistID");
        if(prop!=null) return prop;
        else throw new RuntimeException("Data get_playlistID not found in data.properties file.. ");
    }
    public String playlistIDforUPDATE(){
        String prop = properties.getProperty("update_playlistID");
        if(prop!=null) return prop;
        else throw new RuntimeException("Data update_playlistID not found in data.properties file.. ");
    }
}
