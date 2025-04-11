package com.example.syncdatamarket.utils;

import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;

import java.util.HashMap;
import java.util.Map;

public class Proof {

    private static final String priKey = "bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47";
    public Map<String, Object> provideProof(String account, String newId,String tokenName){
        Map<String, Object> map = new HashMap<>();
        long timestamp = System.currentTimeMillis();
        map.put("account",account);
        map.put("newId/amount",newId);
        map.put("timestamp", Long.toString(timestamp));
        byte[] contentHashBytes = Hash.sha3(new String(account.toLowerCase()+ newId +  Long.toString(timestamp)+tokenName) .getBytes());
        String contentHashHex = Hex.toHexString(contentHashBytes);
        Credentials credentials = Credentials.create(priKey);
        Sign.SignatureData signMessage = Sign.signPrefixedMessage(contentHashBytes, credentials.getEcKeyPair());
        String signResultData = "0x" + Hex.toHexString(signMessage.getR()) + Hex.toHexString(signMessage.getS()) + Hex.toHexString(signMessage.getV());
        map.put("signResultData",signResultData);
        return  map;
    }
}
