package com.kingbom.blockchain;

import java.security.MessageDigest;

public class StringUtil {

    public static String toSha256(String value) {
        try {
            byte[] bytes = MessageDigest.getInstance("SHA-256")
                           .digest(value.getBytes("UTF-8"));
            StringBuffer buffer = new StringBuffer();
            for (byte b : bytes) buffer.append(String.format("%02x", b));
            return buffer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
