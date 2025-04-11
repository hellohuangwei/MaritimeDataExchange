package com.example.syncdatamarket.pojo;

public class OrderInfoPojo {

    String txHash;
    String orderHash;
    String assetData;
    String  orderData;
    String callData;
    String  signature;
    String  timesTamp;
    String  order_hash;

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

    public String getOrderHash() {
        return orderHash;
    }

    public void setOrderHash(String orderHash) {
        this.orderHash = orderHash;
    }

    public String getAssetData() {
        return assetData;
    }

    public void setAssetData(String assetData) {
        this.assetData = assetData;
    }

    public String getOrderData() {
        return orderData;
    }

    public void setOrderData(String orderData) {
        this.orderData = orderData;
    }

    public String getCallData() {
        return callData;
    }

    public void setCallData(String callData) {
        this.callData = callData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(String timesTamp) {
        this.timesTamp = timesTamp;
    }



}
