package com.kingbom.blockchain;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BlockTest {

    private List<Block> blocks = new ArrayList<>();
    private int prefix = 4;
    private String prefixString = new String(new char[prefix]).replace('\0', '0');

    @Before
    public void setUp() {

        Block genesisBlock = new Block("The is the Genesis Block.", "0", new Date().getTime());
        genesisBlock.mineBlock(prefix);
        blocks.add(genesisBlock);

        Block firstBlock = new Block("The is the First Block.", blocks.get(blocks.size() - 1).getHash(), new Date().getTime());
        firstBlock.mineBlock(prefix);
        blocks.add(firstBlock);
    }

    @Test
    public void givenBlockChain_whenSecondBlockChain_shouldBeSuccess() {
        Block secondBlock = new Block("The is a Second Block.", blocks.get(blocks.size() - 1).getHash(), new Date().getTime());
        secondBlock.mineBlock(prefix);
        blocks.add(secondBlock);
        assertTrue(secondBlock.getHash().substring(0, prefix).equals(prefixString));
    }
}
