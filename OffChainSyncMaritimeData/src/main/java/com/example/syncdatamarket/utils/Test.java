package com.example.syncdatamarket.utils;


import com.example.syncdatamarket.pojo.AssetPojo;

public class Test {
    public static void main(String[] args) {

      Decode decode = new Decode();
        AssetPojo assetPojo = new AssetPojo();
        assetPojo= decode.decodeAssetData("0x00000000000000000000000091bf10fc9c00798e5c8af182e77e668a858f738d0000000000000000000000000000000000000000000000000000000000000064");
        System.out.println( );
    }

}
