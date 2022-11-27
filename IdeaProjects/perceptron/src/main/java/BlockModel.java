import lombok.Data;

@Data
public class BlockModel {
    private String prevhash;
    private DataModel data;
    private String signature;
    private String info;
    private String ts;
    private String arbitersignature;

    // return as normalized JSON object
    public String toString() {
        return "{" +
                "\"prevhash\":\"" + prevhash + "\"," +
                "\"data\":" + data.toString() + "," +
                "\"signature\":\"" + signature + "\"," +
                "\"info\":\"" + info + "\"," +
                "\"ts\":\"" + ts + "\"," +
                "\"arbitersignature\":\"" + arbitersignature + "\"," +
                "\"info\":\"" + info + "\"}";
    }
}