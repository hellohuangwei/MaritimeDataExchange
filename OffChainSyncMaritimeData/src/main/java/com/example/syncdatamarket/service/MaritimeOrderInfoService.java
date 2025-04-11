package com.example.syncdatamarket.service;


import com.example.syncdatamarket.dao.MaritimeDataDao;
import com.example.syncdatamarket.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaritimeOrderInfoService {

    @Autowired
    private MaritimeDataDao orderDao;


    public boolean insertOrderdataService(OrderInfoPojo orderInfoPojo) {
        orderDao.insertOrderdata(orderInfoPojo.getTxHash(),orderInfoPojo.getOrderHash(),orderInfoPojo.getAssetData(),orderInfoPojo.getOrderData(),
                orderInfoPojo.getCallData(),orderInfoPojo.getSignature(),orderInfoPojo.getTimesTamp(),orderInfoPojo.getOrder_hash());
        return true;
    }

    public  boolean insertOrderState(String callAddress,String order_hash, int state){
        return  orderDao.insertOrderState(callAddress,order_hash,state);
    }

    public  boolean updateOrderState(String order_hash, int state){
        return  orderDao.updateOrderState(order_hash,state);
    }

    public boolean insertOrderCancelData(CancelOrderPojo cancelOrderPojo ){
        return  orderDao.insertOrderCancelData(cancelOrderPojo.getOrder_hash(), cancelOrderPojo.getTxHash(),cancelOrderPojo.getTimestamp(),cancelOrderPojo.getCalladdress());
    }

    public  boolean insertOrderMatchedData(OrderMatchPojo orderMatchPojo){
        return orderDao.insertOrderMatchedData(orderMatchPojo.getFirstCaller(),orderMatchPojo.getSeccondCaller(),orderMatchPojo.getFirstHashOrder(),orderMatchPojo.getSeccondHashOrder(),orderMatchPojo.getTimestamp());
    }

    public boolean insertOrderHashInfo(OrderHashInfoPojo orderHashInfoPojo){
        return  orderDao.insertOrderHashInfo(orderHashInfoPojo.getOrderHash(),orderHashInfoPojo.getMaker(),orderHashInfoPojo.getSalt(),Long.parseLong(orderHashInfoPojo.getListingTime()), Long.parseLong(orderHashInfoPojo.getExpirationTime()),orderHashInfoPojo.getOffer());
    }

    public  boolean insertOrderPrice(String orderHash, OrderDataPojo orderDataPojo, AssetPojo assetPojo){
        return  orderDao.insertOrderPrice(orderHash,assetPojo.getTokenAddress(),assetPojo.getNum(),orderDataPojo.getTokenAddress(),orderDataPojo.getId(),orderDataPojo.getNum());
    }

}
