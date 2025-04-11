package com.example.syncdatamarket.dao;


import com.example.syncdatamarket.mapper.SyncBlockNumberMapper;
import com.example.syncdatamarket.pojo.SyncBlockNumberPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class BlockNumberDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SyncBlockNumberPojo showContractInfo(int id) {
        String sql = "select end_blockNum,start_blockNum from syncblocknumber  where  id =?";
        SyncBlockNumberPojo syncBlockNumberPojo = jdbcTemplate.queryForObject(sql, new SyncBlockNumberMapper(),id);
        return syncBlockNumberPojo;
    }



    public boolean setBlockNumberPojo(String start, String end,int id) {
        final String sql = "update syncblocknumber set start_blockNum=?,end_blockNum=? where id =?";
        try {
            jdbcTemplate.update(sql, new Object[]{start, end , id});
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
}
