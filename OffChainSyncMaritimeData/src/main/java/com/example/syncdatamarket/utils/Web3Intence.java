package com.example.syncdatamarket.utils;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class Web3Intence {

    private static Web3j web3j = null;

    private static volatile Web3j instance = null;

    private Web3Intence(){}
    public static Web3j getInstance(String chainUrl) {
        if (instance == null) {
            synchronized (Web3j.class) {
                if (instance == null) {
                    instance = Web3j.build(new HttpService((chainUrl)));
                }
            }
        }
        return instance;
    }
}