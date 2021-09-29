package com.redrain.common.utils;

import java.util.UUID;

public class TokenUtils {
    public static String createToken() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return uuid;
    }

    public static void main(String[] args) {
        System.out.println(createToken());
    }
}
