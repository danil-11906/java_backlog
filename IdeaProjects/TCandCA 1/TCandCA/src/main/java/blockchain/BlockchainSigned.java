package blockchain;

import model.Block;
import model.BlockModel;
import org.bouncycastle.util.encoders.Hex;
import service.BlockService;
import utils.Util;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BlockchainSigned extends BlockChainAbstract {

    private static List<Block> blockchain = new ArrayList<>();
    private KeyPair keyPair;
    private static Arbiter arbiter = new Arbiter();
    private PublicKey arbiterKey;

    public BlockchainSigned(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public void setArbiterKey(PublicKey arbiterKey) {
        this.arbiterKey = arbiterKey;
    }

    @Override
    public void makeBlockChain() {
        byte[] prevHash = null;

        for (int i = 0; i < BC_LENGTH; i++) {
            Block block = new Block(i);
            block.addData("data", "data " + i + "");
            block.addData("timestamp", new Date().toString());
            block.setPrevHash(prevHash);

            try {
                prevHash = Util.getHash(block);

                ArbiterModel arbiterModel = arbiter.getSignature(prevHash);
                block.setBlockSignTimestamp(arbiterModel.getTimestamp());
                block.setBlockSign(arbiterModel.getSignature());
            } catch (Exception e) {
                e.printStackTrace();
            }
            blockchain.add(block);

            BlockService blockService = new BlockService();
            try {
                if(i == 0) {
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
            System.out.println("BLOCK NUMBER " + block.getBlockNum());
            System.out.println("prev hash: " + (block.getPrevHash() != null ? new String(Hex.encode(block.getPrevHash())) : ""));
            System.out.println(block.getData());
            System.out.println("hash: " + new String(Hex.encode(Util.getHash(block))));
//            System.out.println("signature: " + new String(Hex.encode(block.getSign())));
            System.out.println("block sign: " + new String(Hex.encode(block.getBlockSign())));
            System.out.println();
        }
    }

    @Override
    public boolean verification() throws UnsupportedEncodingException, GeneralSecurityException {
        byte[] prevHash = Util.getHash(blockchain.get(0));
        for (int i = 1; i < BC_LENGTH; i++) {
            if (!Arrays.equals(prevHash, blockchain.get(i).getPrevHash())) {
                return false;
            }

            byte[] dHash = Util.getHash(blockchain.get(i));

            if (!Util.verifyRSAPSSSignature(keyPair.getPublic(), dHash, blockchain.get(i).getSign())) {
                return false;
            }

            prevHash = Util.getHash(blockchain.get(i));

            try {
                if (Util.verifyArbiterSignature(arbiterKey, blockchain.get(i).getBlockSignTimestamp(), prevHash, blockchain.get(i).getBlockSign())) {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public void damage() {
        blockchain.get(3).removeData("data");
        blockchain.get(3).addData("data", "damaged data");
    }
}
