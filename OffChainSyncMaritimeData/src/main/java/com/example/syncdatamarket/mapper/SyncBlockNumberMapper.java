package com.example.syncdatamarket.mapper;


import com.example.syncdatamarket.pojo.SyncBlockNumberPojo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class SyncBlockNumberMapper implements RowMapper<SyncBlockNumberPojo> {

    @Override
    public SyncBlockNumberPojo mapRow(ResultSet resultSet, int i) throws SQLException {

        String start = resultSet.getString("start_blockNum");
        String end = resultSet.getString("end_blockNum");

        SyncBlockNumberPojo syncBlockNumberPojo = new SyncBlockNumberPojo();
        syncBlockNumberPojo.setStart(start);
        syncBlockNumberPojo.setEnd(end);
        return syncBlockNumberPojo;
    }

}
