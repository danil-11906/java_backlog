package blockchain;

import model.Block;
import model.BlockModel;
import org.bouncycastle.util.encoders.Hex;
import service.BlockService;
import utils.Util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BlockchainSimple extends BlockChainAbstract {
    private static List<Block> blockchain = new ArrayList<>();

    @Override
    public void makeBlockChain() {
        byte[] prevHash = null;

        for (int i = 0; i < BC_LENGTH; i++) {
            Block block = new Block(i);
            block.addData("data", "data " + i);
            block.addData("timestamp", new Date().toString());
            block.setPrevHash(prevHash);

            try {
                prevHash = Util.getHash(block);
            } catch (Exception e) {
                e.printStackTrace();
            }

            blockchain.add(block);
            BlockService blockService = new BlockService();
            try {// начинаем заносить в бд данные
                if (i == 0) {
                    BlockModel blockModel = new BlockModel(block.getBlockNum(), null, new String(Hex.encode(Util.getHash(block))));
                    blockService.create(blockModel);
                } else {

                    BlockModel blockModel = new BlockModel(block.getBlockNum(), new String(Hex.encode(block.getPrevHash())), new String(Hex.encode(Util.getHash(block))));
                    blockService.create(blockModel);
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void print() throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchProviderException {
        for (int i = 0; i < BC_LENGTH; i++) {
            Block block = blockchain.get(i);
            BlockService blockService = new BlockService();
            try {
                BlockModel blockModel = blockService.get(new String(Hex.encode(block.getPrevHash())));
                System.out.println("BLOCK NUMBER " + blockModel.getBlockNumber());
                System.out.println("prev hash: " + blockModel.getPrevHash());
                System.out.println("hash: " + blockModel.getHash());
            } catch (NullPointerException e) {
                System.out.println("BLOCK NUMBER " + block.getBlockNum());
                System.out.println("prev hash: " + (block.getPrevHash() != null ? new String(Hex.encode(block.getPrevHash())) : ""));
                System.out.println("Data: " + block.getData());
                System.out.println("hash: " + new String(Hex.encode(Util.getHash(block))));
                System.out.println();
            }
        }
    }

    @Override
    public boolean verification() throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchProviderException {
        byte[] prevHash = Util.getHash(blockchain.get(0));
        for (int i = 1; i < BC_LENGTH; i++) {
            if (!Arrays.equals(prevHash, blockchain.get(i).getPrevHash())) {
                return false;
            }

            prevHash = Util.getHash(blockchain.get(i));
        }

        return true;
    }

    @Override
    public void damage() {
        blockchain.get(3).removeData("data");
        blockchain.get(3).addData("data", "damaged data");
    }
}
