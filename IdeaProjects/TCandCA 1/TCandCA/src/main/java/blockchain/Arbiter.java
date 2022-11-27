package blockchain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;
import java.nio.charset.Charset;

public class Arbiter {
    private final OkHttpClient httpClient = new OkHttpClient();
    private static final String URL_PUBLIC = "http://89.108.115.118/ts/public";
    private static final String URL_DIG = "http://89.108.115.118/ts?digest=";

    public String getPublicKey() throws Exception {
        Request request = new Request.Builder()
                .url(URL_PUBLIC)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            return response.body().string();
        }
    }

    public ArbiterModel getSignature(byte[] blockHash) throws Exception{
        Request request = new Request.Builder()
                .url(URL_DIG + new String(Hex.encode(blockHash)))
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode objectNode = objectMapper.readTree(response.body().string());
            String timestamp = objectNode.get("timeStampToken").get("ts").toString().replace("\"", "");
            byte[] signature = Hex.decode(objectNode.get("timeStampToken").get("signature").toString().replace("\"", "").getBytes(Charset.defaultCharset()));

            ArbiterModel arbiterSignature = new ArbiterModel(timestamp, signature);

            return arbiterSignature;
        }
    }
}
