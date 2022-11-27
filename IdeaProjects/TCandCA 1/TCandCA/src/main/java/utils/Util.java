package utils;

import model.Block;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static org.bouncycastle.util.encoders.Hex.decode;

public class Util {
    public static final String DIGEST_ALGORITHM = "SHA-256";
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGN_ALGORITHM = "SHA256withRSAandMGF1";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] getHash(Block block) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException {
        byte[] dataNNum = concat(block.getData().getBytes(StandardCharsets.UTF_8), String.valueOf(block.getBlockNum()).getBytes(StandardCharsets.UTF_8));
        byte[] prevHashNDataSign = concat(block.getPrevHash(), block.getSign());
        byte[] result = concat(dataNNum, prevHashNDataSign);

        MessageDigest digest = MessageDigest.getInstance(DIGEST_ALGORITHM,"BC");

        return digest.digest(result);
//        String info = "";
//        for (String s : block.getData()) {
//            info = info + s;
//        }
//
//        MessageDigest digest = MessageDigest.getInstance(DIGEST_ALGORITHM,"BC"); //BouncyCastleProvider
//
//        byte[] result = digest.digest(concat(block.getPrevHash(),info.getBytes(StandardCharsets.UTF_8)));
//        return result;
    }

    public static byte[] concat(byte[] a, byte[] b) {
        if (a == null) return b;
        if (b == null) return a;
        int len_a = a.length;
        int len_b = b.length;
        byte[] C = new byte[len_a + len_b];
        System.arraycopy(a, 0, C, 0, len_a);
        System.arraycopy(b, 0, C, len_a, len_b);
        return C;
    }

    public static KeyPair loadKeys() throws Exception {

        byte[] publicKeyHex = Files.readAllBytes(Paths.get("src/main/resources/public.key"));
        byte[] privateKeyHex = Files.readAllBytes(Paths.get("src/main/resources/private.key"));

        PublicKey publicKey = convertArrayToPublicKey(decode(publicKeyHex),KEY_ALGORITHM);
        PrivateKey privateKey = convertArrayToPrivateKey(decode(privateKeyHex),KEY_ALGORITHM);

        KeyPair keyPair = new KeyPair(publicKey, privateKey);
        return keyPair;
    }


    public static PublicKey convertArrayToPublicKey(byte encoded[], String algorithm) throws Exception {
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

        return pubKey;
    }

    public static PrivateKey convertArrayToPrivateKey(byte encoded[], String algorithm) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PrivateKey priKey = keyFactory.generatePrivate(keySpec);
        return priKey;
    }

    public static byte[] generateRSAPSSSignature(PrivateKey privateKey, byte[] input) throws GeneralSecurityException {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM, "BC");
        signature.initSign(privateKey);
        signature.update(input);
        return signature.sign();
    }

    public static boolean verifyRSAPSSSignature(PublicKey publicKey, byte[] input, byte[] encSignature) throws GeneralSecurityException {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM, "BC");
        signature.initVerify(publicKey);
        signature.update(input);
        return signature.verify(encSignature);
    }

    public static PublicKey convertStringToPublicKey(String hash) throws Exception{
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(decode(hash));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

        return pubKey;
    }

    public static boolean verifyArbiterSignature(PublicKey publicKey, String ts, byte[] input, byte[] encSignature) throws Exception {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM, "BC");
        signature.initVerify(publicKey);
        signature.update(concat(ts.getBytes(StandardCharsets.UTF_8), input));
        return signature.verify(encSignature);
    }
}
