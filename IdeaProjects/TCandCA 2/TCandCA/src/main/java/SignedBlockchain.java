import blockchain.Arbiter;
import blockchain.BlockchainSigned;
import utils.Util;

import java.security.KeyPair;
import java.security.PublicKey;

public class SignedBlockchain {
    private static KeyPair keyPair;
    private static Arbiter arbiter = new Arbiter();
    private static PublicKey arbiterKey;

    public static void main(String[] args) {
        try {
            keyPair = Util.loadKeys();
            arbiterKey = Util.convertStringToPublicKey(arbiter.getPublicKey());

            BlockchainSigned blockchainSigned = new BlockchainSigned(keyPair);
            blockchainSigned.setArbiterKey(arbiterKey);
            blockchainSigned.makeBlockChain();
            blockchainSigned.print();
            System.out.println("Verify result: " + blockchainSigned.verification());
            blockchainSigned.damage();
            blockchainSigned.print();
            System.out.println("Verify result: " + blockchainSigned.verification());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
