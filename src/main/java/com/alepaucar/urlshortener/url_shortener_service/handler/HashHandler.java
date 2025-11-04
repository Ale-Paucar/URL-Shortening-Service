package com.alepaucar.urlshortener.url_shortener_service.handler;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.UUID;

public class HashHandler {
    private static final SecureRandom RANDOM = new SecureRandom();
    public static String generateNewShortCode(){
        String randomCodeLong[] = UUID.randomUUID().toString().split("-");
        //Arrays.stream(randomCodeLong).forEach(System.out::println);
        //we take pseudorandom portion from a random uuid
        //The length of the code and the indexes were chosen without following a logical guideline, in other words, just because
        String randomCodeShort =
                randomUpperOrLowerCase(randomCodeLong[0].substring(6)) +
                randomUpperOrLowerCase(randomCodeLong[1].substring(0,2)) +
                randomUpperOrLowerCase(randomCodeLong[3].substring(2));
        return randomCodeShort;

        //Another way is to create a string containing uppercase and lowercase letters and numbers, and randomly choose a character from the index of this string
    }

    private static  String randomUpperOrLowerCase(String code){
        return RANDOM.nextBoolean() ? code.toLowerCase() : code.toUpperCase();
    }
}
