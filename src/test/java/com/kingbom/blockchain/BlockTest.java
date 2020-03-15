package com.kingbom.blockchain;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BlockTest {
    private List<Block> blocks = new ArrayList<>();
    private int difficulty = 4;
    private String prefixString = new String(new char[difficulty]).replace('\0', '0');

    @Before
    public void setUp() {
        Block genesisBlock = new Block("The is the Genesis Block.", "0", new Date().getTime());
        genesisBlock.mineBlock(difficulty);
        blocks.add(genesisBlock);
        Block firstBlock = new Block("The is the First Block.", blocks.get(blocks.size() - 1).getHash(), new Date().getTime());
        firstBlock.mineBlock(difficulty);
        blocks.add(firstBlock);
    }

    @Test
    public void givenBlockChain_whenSecondBlockChain_shouldBeSuccess() {
        String previousHash = blocks.get(blocks.size() - 1).getHash();
        Block secondBlock = new Block("The is a Second Block.", previousHash, new Date().getTime());
        secondBlock.mineBlock(difficulty);
        blocks.add(secondBlock);

        assertEquals(secondBlock.getHash().substring(0, difficulty), prefixString);
        assertEquals(secondBlock.getPreviousHash(), previousHash);
    }

    @Test
    public void givenBlockChain_whenValidated_shouldBeValidAll() {
        for (int index = 0; index < blocks.size(); index++) {
            Block currentBlock = blocks.get(index);
            String previousHash = getPreviousHash(index);
            assertEquals(currentBlock.getPreviousHash(), previousHash);
            assertEquals(currentBlock.getHash(), currentBlock.calculateBlockHash());
            assertEquals(currentBlock.getHash().substring(0, difficulty), prefixString);
        }
    }

    private String getPreviousHash(int index){
        return index == 0 ? "0" : blocks.get(index - 1).getHash();
    }
}
