package blockchain;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public interface BlockChain {
    void makeBlockChain();

    void print() throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchProviderException;

    boolean verification() throws UnsupportedEncodingException, GeneralSecurityException;

    void damage();
}
