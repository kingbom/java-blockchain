package com.kingbom.blockchain;

import java.security.Key;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.util.Base64;

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

    public static String getStringFromKey(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());

    }
}
