import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import utils.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.security.*;

public class NewKey {
    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator rsa = null;
        try (Writer publicKeyWriter = new FileWriter(new File("publik.key"));
             Writer privateKeyWriter = new FileWriter(new File("private.key"))) {
            //rsa = KeyPairGenerator.getInstance("RSA", new BouncyCastleProvider());
            rsa = KeyPairGenerator.getInstance(Util.KEY_ALGORITHM, "BC");
            rsa.initialize(1024,new SecureRandom());
            KeyPair keyPair = rsa.generateKeyPair();

            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            privateKeyWriter.write(new String(Hex.encode(privateKey.getEncoded())));
            publicKeyWriter.write(new String(Hex.encode(publicKey.getEncoded())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
