package com.kingbom.blockchain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.security.MessageDigest;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Block {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String data, String previousHash, long timeStamp) {
        this.hash = calculateBlockHash();
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = timeStamp;
    }

    public String calculateBlockHash() {
        String dataToHash = previousHash + timeStamp + nonce + data;
        byte[] bytes = digest(dataToHash);
        return bytesAsString(bytes);
    }

    public String mineBlock(int difficulty) {
        String prefixString = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(prefixString)) {
            nonce++;
            hash = calculateBlockHash();
        }
        return hash;
    }

    private byte[] digest(String dataToHash) {
        try {
             return MessageDigest.getInstance("SHA-256")
                    .digest(dataToHash.getBytes("UTF-8"));
        } catch (Exception ex) {
           return new byte[0];
        }
    }

    private String bytesAsString(byte[] bytes) {
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) buffer.append(String.format("%02x", b));
        return buffer.toString();
    }
}
