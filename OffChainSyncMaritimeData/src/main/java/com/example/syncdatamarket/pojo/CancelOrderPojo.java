package com.example.syncdatamarket.pojo;

public class CancelOrderPojo {

    String calladdress;
    String order_hash;
    String timestamp;
    String txHash;

    public String getCalladdress() {
        return calladdress;
    }

    public void setCalladdress(String calladdress) {
        this.calladdress = calladdress;
    }

    public String getOrder_hash() {
        return order_hash;
    }

    public void setOrder_hash(String order_hash) {
        this.order_hash = order_hash;
    }


    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
