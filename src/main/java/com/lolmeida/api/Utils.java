package com.lolmeida.api;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

import com.lolmeida.domain.entity.database.User;

public  class Utils {
    public static long currentTime = System.currentTimeMillis(); //Instant.now().toEpochMilli();
    public static LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("UTC"));


    public  static String generateRandomString(){
        int leftLimit = 48; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(12)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public static String activeUser() {
        return "lolmeida@gmail.com";
    }


    public static User createUser() {
        return User
                .builder()
                .id("1")
                .name("Lolmeida")
                .mail("lou@nas.val")
                .phoneNumber("123456789")
                .address("Rua do Loureiro")
                .build();
    }
}
