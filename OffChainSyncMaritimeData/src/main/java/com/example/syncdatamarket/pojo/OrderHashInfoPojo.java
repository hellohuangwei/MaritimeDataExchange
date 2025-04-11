package com.example.syncdatamarket.pojo;

public class OrderHashInfoPojo {

    String orderHash;
    String maker;
    String salt;
    String ListingTime;
    String expirationTime;
    String offer;

    public String getOrderHash() {
        return orderHash;
    }

    public void setOrderHash(String orderHash) {
        this.orderHash = orderHash;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getListingTime() {
        return ListingTime;
    }

    public void setListingTime(String listingTime) {
        ListingTime = listingTime;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }
}
