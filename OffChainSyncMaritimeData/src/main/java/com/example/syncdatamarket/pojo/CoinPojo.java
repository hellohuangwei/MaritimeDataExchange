package com.example.syncdatamarket.pojo;

import java.math.BigInteger;

public class CoinPojo {

    String call;
    BigInteger amount;
    BigInteger timestamp;

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(BigInteger timestamp) {
        this.timestamp = timestamp;
    }

}
