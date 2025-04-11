package com.example.syncdatamarket.utils;


import com.example.syncdatamarket.pojo.AssetPojo;
import com.example.syncdatamarket.pojo.OrderDataPojo;
import com.example.syncdatamarket.pojo.OrderHashInfoPojo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;

import java.util.Arrays;
import java.util.Collections;

@Component
public class Decode {

    private OrderHashInfoPojo orderHashInfoPojo;
    private OrderDataPojo orderDataPojo;
    private AssetPojo assetPojo;

    public OrderHashInfoPojo decodeOrderHash(String orderHash,String data){
        orderHashInfoPojo = new OrderHashInfoPojo();
        Function function =
                new Function(
                        "function",
                        Collections.<Type>emptyList(),
                        Arrays.asList(
                                new TypeReference<Address>() {},
                                new TypeReference<Uint256>() {},
                                new TypeReference<Uint>() {},
                                new TypeReference<Uint>() {},
                                new TypeReference<Bool>() {}));

        orderHashInfoPojo.setOrderHash(orderHash);
        orderHashInfoPojo.setMaker(FunctionReturnDecoder.decode(data,function.getOutputParameters()).get(0).toString());
        orderHashInfoPojo.setSalt(FunctionReturnDecoder.decode(data,function.getOutputParameters()).get(1).getValue().toString());
        orderHashInfoPojo.setListingTime(FunctionReturnDecoder.decode(data,function.getOutputParameters()).get(2).getValue().toString());
        orderHashInfoPojo.setExpirationTime(FunctionReturnDecoder.decode(data,function.getOutputParameters()).get(3).getValue().toString());
        orderHashInfoPojo.setOffer(FunctionReturnDecoder.decode(data,function.getOutputParameters()).get(4).getValue().toString());
        return  orderHashInfoPojo;
    }

    public OrderDataPojo decodeOrderData(String data){
        orderDataPojo = new OrderDataPojo();
        Function function =
                new Function(
                        "function",
                        Collections.<Type>emptyList(),
                        Arrays.asList(
                                new TypeReference<Address>() {},
                                new TypeReference<Uint256>() {},
                                new TypeReference<Uint256>() {}));

        orderDataPojo.setTokenAddress(FunctionReturnDecoder.decode(data,function.getOutputParameters()).get(0).toString());
        orderDataPojo.setId(FunctionReturnDecoder.decode(data,function.getOutputParameters()).get(1).getValue().toString());
        orderDataPojo.setNum(Integer.parseInt(FunctionReturnDecoder.decode(data,function.getOutputParameters()).get(2).getValue().toString()));
        return  orderDataPojo;
    }

    public AssetPojo decodeAssetData(String data){
        assetPojo = new AssetPojo();
        Function function =
                new Function(
                        "function",
                        Collections.<Type>emptyList(),
                        Arrays.asList(
                                new TypeReference<Address>() {},
                                new TypeReference<Uint256>() {}));

        assetPojo.setTokenAddress(FunctionReturnDecoder.decode(data,function.getOutputParameters()).get(0).toString());
        assetPojo.setNum(FunctionReturnDecoder.decode(data,function.getOutputParameters()).get(1).getValue().toString());
        return  assetPojo;
    }
}
