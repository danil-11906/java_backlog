package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Block {
    private int blockNum;
    private JsonNode data;
    private byte[] prevHash;
    private byte[] sign;
    private byte[] blockSign;
    private String blockSignTimestamp;

    public Block(int blockNum) {
        this.blockNum = blockNum;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.data = objectMapper.readTree("{}");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public int getBlockNum() {
        return blockNum;
    }

    public void setBlockNum(int blockNum) {
        this.blockNum = blockNum;
    }

    public String getData() {
        return data.toString();
    }

    public void addData(String key, String value) {
        ((ObjectNode) this.data).put(key, value);
    }

    public void removeData(String key){
        if (this.data.has(key)) {
            ((ObjectNode) this.data).remove(key);
        }
    }

    public void addDataFromString(String data){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.data = objectMapper.readTree(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public byte[] getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(byte[] prevHash) {
        this.prevHash = prevHash;
    }

    public byte[] getBlockSign() {
        return blockSign;
    }

    public void setBlockSign(byte[] blockSign) {
        this.blockSign = blockSign;
    }

    public byte[] getSign() {
        return sign;
    }

    public void setSign(byte[] sign) {
        this.sign = sign;
    }

    public String getBlockSignTimestamp() {
        return blockSignTimestamp;
    }

    public void setBlockSignTimestamp(String blockSignTimestamp) {
        this.blockSignTimestamp = blockSignTimestamp;
    }
//    private int blockNum;
//    private List<String> data = new ArrayList<>();
//    private byte[] prevHash;
//    private byte[] sign;
//
//    public Block(int blockNum) {
//        this.blockNum = blockNum;
//    }
//
//    public int getBlockNum() {
//        return blockNum;
//    }
//
//    public void setBlockNum(int blockNum) {
//        this.blockNum = blockNum;
//    }
//
//    public List<String> getData() {
//        return data;
//    }
//
//    public void setData(List<String> data) {
//        this.data = data;
//    }
//
//    public byte[] getPrevHash() {
//        return prevHash;
//    }
//
//    public void setPrevHash(byte[] prevHash) {
//        this.prevHash = prevHash;
//    }
//
//    public byte[] getSign() {
//        return sign;
//    }
//
//    public void setSign(byte[] sign) {
//        this.sign = sign;
//    }
}
