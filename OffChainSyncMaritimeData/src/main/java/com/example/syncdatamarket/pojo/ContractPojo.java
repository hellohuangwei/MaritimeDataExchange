package com.example.syncdatamarket.pojo;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class ContractPojo {

    BigInteger gasPrice;
    BigInteger gasLimit;
    String chainRpc;
    String contractAddress;
    String credentials;
    long chainId;

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }


    public BigInteger getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(BigInteger gasPrice) {
        this.gasPrice = gasPrice;
    }

    public BigInteger getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(BigInteger gasLimit) {
        this.gasLimit = gasLimit;
    }

    public String getChainRpc() {
        return chainRpc;
    }

    public void setChainRpc(String chainRpc) {
        this.chainRpc = chainRpc;
    }


    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public long getChainId() {
        return chainId;
    }

    public void setChainId(long chainId) {
        this.chainId = chainId;
    }

}
