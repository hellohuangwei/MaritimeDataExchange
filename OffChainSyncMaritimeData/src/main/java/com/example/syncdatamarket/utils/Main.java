package com.example.syncdatamarket.utils;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ConnectException;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException, ConnectException {
        Web3j client = Web3j.build(new HttpService("https://bsc-dataseed3.binance.org"));
        EthBlockNumber blockNumber = null;
        try {
             blockNumber = client.ethBlockNumber().send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(blockNumber);
        // 获取 nonce 值
//        EthGetTransactionCount ethGetTransactionCount = client
//                .ethGetTransactionCount("0x64f44b31ad0ed4537f94a5c084cfba8945463345", DefaultBlockParameterName.PENDING)
//                .sendAsync().get();
//        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
//        System.out.println(nonce);
//
//        // 构建交易
//        RawTransaction etherTransaction = RawTransaction.createEtherTransaction(
//                nonce,
//                client.ethGasPrice().sendAsync().get().getGasPrice(),
//                DefaultGasProvider.GAS_LIMIT,
//                "0x64f44b31ad0ed4537f94a5c084cfba8945463345",
//                Convert.toWei("0.001", Convert.Unit.ETHER).toBigInteger()
//        );
//        System.out.println(etherTransaction);
//
//        // 加载私钥
//        Credentials credentials = Credentials.create("bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47");
//
//        // 使用私钥签名交易并发送
//        byte[] signature = TransactionEncoder.signMessage(etherTransaction, credentials);
//        String signatureHexValue = Numeric.toHexString(signature);
//        EthSendTransaction ethSendTransaction = client.ethSendRawTransaction(signatureHexValue).sendAsync().get();
//        System.out.println(ethSendTransaction.getResult());
    }
}
