package com.example.syncdatamarket.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MaritimeDataDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insertOrderdata(String txHash,String orderHash,String assetData, String orderData,String callData,String  signature,String timesTamp,String order_hash) {
        final String sql = " insert into orderinfo(txHash,orderHash,assetData,orderData,callData,signature,timesTamp,order_hash)values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,txHash,orderHash,assetData,orderData,callData,signature,timesTamp,order_hash);
        return true;
    }

    public boolean insertOrderState(String callAddress,String order_hash,int state){
        final String sql = " insert into orderstate(callAddress,order_hash,state )values(?,?,?)";
        jdbcTemplate.update(sql,callAddress,order_hash,state);
        return true;
    }

    public boolean insertOrderCancelData(String order_hash, String txHash, String timeStap,String calladdress){
        final  String sql = "insert into cancelorder(order_hash,txHash,timestap,calladdress)values(?,?,?,?)";
        jdbcTemplate.update(sql,order_hash,txHash, timeStap,calladdress);
        return  true;
    }

    public boolean insertOrderMatchedData(String firstCaller,String  seccondcaller, String firstHashOrder,String seccondHashOrder,String timestamp){
        final  String sql = "insert into matchedorderinfo(firstHashOrder,seccondHashOrder,firstcaller,seccondcaller,timestamp)values(?,?,?,?,?)";
        jdbcTemplate.update(sql,firstHashOrder,seccondHashOrder,firstCaller,seccondcaller ,timestamp);
        return  true;
    }

    public boolean insertOrderHashInfo(String orderHash,String maker,String salt,Long ListingTime,Long expirationTime,String offer){
        final  String sql = "insert into orderhashinfo(order_hash,maker,salt,ListingTime,expirationTime,offer)values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,orderHash,maker,salt,ListingTime,expirationTime,offer);
        return  true;
    }

    public boolean insertOrderPrice(String order_hash,String tokenAaddress,String price,String tokenBaddress,String tokenId,int num){
        final  String sql = "insert into orderprice(order_hash,tokenAaddress,price,tokenBaddress,tokenId,num)values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,order_hash,tokenAaddress,price,tokenBaddress,tokenId,num);
        return  true;
    }

    public  boolean updateOrderState(String order_hash,int state){
        String sql = "UPDATE orderstate set state = ? where order_hash = ? ";
        jdbcTemplate.update(sql,new Object[] {state,order_hash});
        return  true;
    }

}
