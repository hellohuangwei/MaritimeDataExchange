package com.example.syncdatamarket.utils;

import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;

import java.math.BigInteger;
import java.security.SignatureException;

public class Web3j {

    static final String MESSAGE_PREFIX = "\u0019Ethereum Signed Message:\n";

    static byte[] getEthereumMessagePrefix(int messageLength) {
        return MESSAGE_PREFIX.concat(String.valueOf(messageLength)).getBytes();
    }

    public static byte[] getEthereumMessageHash(byte[] message) {
        byte[] prefix = getEthereumMessagePrefix(message.length);
        byte[] result = new byte[prefix.length + message.length];
        System.arraycopy(prefix, 0, result, 0, prefix.length);
        System.arraycopy(message, 0, result, prefix.length, message.length);
        return Hash.sha3(result);
    }

    // 钱包私钥
    private static final String priKey = "bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47";
    // 钱包地址
    private static final String walletAddress = "0xfd0cd49de3e8526fce2854b40d9f9ef9c74dfb0f111";

    public static void main(String[] args) {
        // 原文
        String content = "0x000000000000000000000000fd0cd49de3e8526fce2854b40d9f9ef9c74dfb0f000000000000000000000000000000000000000000000000000000000000000a000000000000000000000000000000000000000000000000000000000000000a000000000000000000000000000000000000000000000000000000000000000a";

        System.out.println(Hash.sha3(content));
        // 原文摘要字节数组
        byte[] contentHashBytes = Hash.sha3(content.getBytes());

        // 原文摘要16进制字符串
        String contentHashHex = Hex.toHexString(contentHashBytes);
        Credentials credentials = Credentials.create(priKey);
        Sign.SignatureData signMessage = Sign.signPrefixedMessage(contentHashBytes, credentials.getEcKeyPair());
        System.out.println("contentHashHex      "+contentHashHex);
        System.out.println("R-----------" + Hex.toHexString(signMessage.getR()));
        System.out.println("S-----------" + Hex.toHexString(signMessage.getS()));
        System.out.println("V-----------" + Hex.toHexString( signMessage.getV()));

        // 签名后的字符串
        String signStr = "0x" + Hex.toHexString(signMessage.getR()) + Hex.toHexString(signMessage.getS()) + Hex.toHexString(signMessage.getV());
        System.out.println("signStr=" + signStr);


        // 原文摘要 添加 ETH签名头信息后再生成摘要
        byte[] messageHash = getEthereumMessageHash(Hex.decode(contentHashHex));
        String s = Hex.toHexString(messageHash);
        System.out.println("-------"+ Hex.toHexString(messageHash));
        //通过摘要和签名后的数据，还原公钥
        Sign.SignatureData signatureData = new Sign.SignatureData(signMessage.getV(), signMessage.getR(), signMessage.getS());
        BigInteger publicKey = null;

        try {
            publicKey = Sign.signedMessageHashToKey(messageHash, signatureData);

        } catch (SignatureException e) {
            e.printStackTrace();
        }

        //还原地址 0x5E32cc9cF83e6f346830fc11e207ac4b91213f30
            String parseAddress = "0x" + Keys.getAddress(publicKey);
            System.err.println("Address：：" + parseAddress);
    }

}

//0xf810d86b64fbb1a43ae15fbe5a78cf6fd81b8ec2005a20bc4f0a7e3df2ab77df
//0x6814ebf4639e9da55014c6cbe5bc23c982e8f37ab6c68cab3474b4c0f6a7e62e