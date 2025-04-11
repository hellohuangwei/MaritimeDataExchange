package com.example.syncdatamarket.pojo;

public class OrderMatchPojo {

    String firstCaller;
    String  seccondCaller;
    String firstHashOrder;
    String seccondHashOrder;
    String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFirstCaller() {
        return firstCaller;
    }

    public void setFirstCaller(String firstCaller) {
        this.firstCaller = firstCaller;
    }

    public String getSeccondCaller() {
        return seccondCaller;
    }

    public void setSeccondCaller(String seccondCaller) {
        this.seccondCaller = seccondCaller;
    }

    public String getFirstHashOrder() {
        return firstHashOrder;
    }

    public void setFirstHashOrder(String firstHashOrder) {
        this.firstHashOrder = firstHashOrder;
    }

    public String getSeccondHashOrder() {
        return seccondHashOrder;
    }

    public void setSeccondHashOrder(String seccondHashOrder) {
        this.seccondHashOrder = seccondHashOrder;
    }


}
