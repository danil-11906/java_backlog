import blockchain.BlockchainSimple;

public class SimpleBlockChain {
    private static final int BC_LENGTH = 10;

    public static void main(String[] args) {
        try {
            BlockchainSimple blockchainSimple = new BlockchainSimple();
            blockchainSimple.makeBlockChain();
            blockchainSimple.print();
            System.out.println("Verify result: " + blockchainSimple.verification());
            blockchainSimple.damage();
            blockchainSimple.print();

            System.out.println("Verify result:" + blockchainSimple.verification());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
