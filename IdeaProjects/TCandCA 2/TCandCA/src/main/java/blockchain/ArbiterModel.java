package blockchain;

public class ArbiterModel {
    private String timestamp;
    private byte[] signature;

    public ArbiterModel(String timestamp, byte[] signature) {
        this.timestamp = timestamp;
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }
}
