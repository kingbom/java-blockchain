package com.kingbom.blockchain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
        return StringUtil.toSha256(previousHash + timeStamp + nonce + data);
    }

    public String mineBlock(int difficulty) {
        String prefixString = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(prefixString)) {
            nonce++;
            hash = calculateBlockHash();
        }
        return hash;
    }
}
