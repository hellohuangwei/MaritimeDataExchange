package com.example.syncdatamarket.utils;
import org.web3j.protocol.Web3j;


public class test2 {

    private static GetWeb3j getWeb3j;
    private static Web3j web3j;

    public static Web3j  t1(){
        getWeb3j = GetWeb3j.getInstance();
         web3j = getWeb3j.initWeb3j("xxx");
        System.out.println("web3j"+web3j);
        System.out.println("service:"+ getWeb3j.service);
        return  web3j;
    }

    public static Web3j t2(){
        getWeb3j = GetWeb3j.getInstance();
         web3j = getWeb3j.initWeb3j("xxx");
        System.out.println("web3j"+web3j);
        System.out.println("service:"+getWeb3j.service);
        return  web3j;
    }

    public static Web3j t3(){
        getWeb3j = GetWeb3j.getInstance();
        web3j = getWeb3j.initWeb3j("xxx");
        System.out.println("web3j"+web3j);
        System.out.println("service:"+getWeb3j.service);
        return  web3j;
    }

    public static void main(String[] args) {
        System.out.println(t1());
        System.out.println(t2());
        System.out.println(t3());
    }
}
