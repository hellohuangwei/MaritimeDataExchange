package com.example.syncdatamarket.utils;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class GetWeb3j {

    public static HttpService service = null;
    private static Web3j web3j = null;

    private static volatile GetWeb3j instance = null;
    private GetWeb3j(){}
    public static GetWeb3j getInstance() {
        if (instance == null) {
            synchronized (GetWeb3j.class) {
                if (instance == null) {
                    instance = new GetWeb3j();
                }
            }
        }
        return instance;
    }


    private static HttpService getService(String url) {
        if (service == null) {
            service = new HttpService(url);
        }
        return service;
    }

    public static Web3j initWeb3j(String chainUrl) {
        if (null == web3j) {
            return Web3j.build(getService(chainUrl));
        }
        return web3j;
    }
}