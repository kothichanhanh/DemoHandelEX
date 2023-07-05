package com.msb.epay.common;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Encode {
    public static String enscriptSHA256(String string){
        return Hashing.sha256()
                .hashString(string, StandardCharsets.UTF_8)
                .toString();
    }
}
