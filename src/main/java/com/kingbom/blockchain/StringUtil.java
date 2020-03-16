package com.kingbom.blockchain;

import java.security.*;
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

    public static byte [] applyECDSASig(PrivateKey privateKey, String input) {
        try {
            Signature dsa;
            dsa = Signature.getInstance("ECDSA", "BC");
            dsa.initSign(privateKey);
            byte[] strByte = input.getBytes();
            dsa.update(strByte);
            byte[] realSig = dsa.sign();
            return realSig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
        try {
            Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(signature);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStringFromKey(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
