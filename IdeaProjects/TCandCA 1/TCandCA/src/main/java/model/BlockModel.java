package model;

public class BlockModel {
    private int id;
    private int blocNumber;
    private String prevHash;
    private String hash;

    public BlockModel(int id, int blocNumber, String prevHash, String hash) {
        this.id = id;
        this.blocNumber = blocNumber;
        this.prevHash = prevHash;
        this.hash = hash;
    }

    public BlockModel(int blocNumber, String prevHash, String hash) {
        this.blocNumber = blocNumber;
        this.prevHash = prevHash;
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public int getBlockNumber() {
        return blocNumber;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getHash() {
        return hash;
    }

    public void setBlocNumber(int blocNumber) {
        this.blocNumber = blocNumber;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
