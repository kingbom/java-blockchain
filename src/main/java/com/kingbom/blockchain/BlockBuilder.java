package com.kingbom.blockchain;

public class BlockBuilder {
    private String data;
    private String previousHash;
    private long timeStamp;
    private String hash;
    private int nonce;

    public BlockBuilder setData(String data) {
        this.data = data;
        return this;
    }

    public BlockBuilder setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
        return this;
    }

    public BlockBuilder setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public BlockBuilder setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public BlockBuilder setNonce(int nonce) {
        this.nonce = nonce;
        return this;
    }

    public Block createBlock() {
        return new Block(data, previousHash, timeStamp);
    }
}