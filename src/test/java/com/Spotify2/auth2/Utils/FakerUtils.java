package com.Spotify2.auth2.Utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName(){
        Faker faker = new Faker();
        return faker.regexify("[A-Za-z0-9 -_@]{10}");
    }

    public static String generateDescription(){
        Faker faker = new Faker();
        return faker.regexify("[A-Za-z0-9 -_@#]{20}");
    }
}
