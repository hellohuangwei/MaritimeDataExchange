package com.example.syncdatamarket.dao;


import com.example.syncdatamarket.mapper.ContractMapper;
import com.example.syncdatamarket.pojo.ContractPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class ContractDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insertContractdata(long chainId, String credentials, String contractAddress, String chainRpc, BigInteger gasPrice, BigInteger gasLimit) {
        final String sql = "insert into contractInfo(chainId,credentials,contractAddress,chainRpc,gasPrice,gasLimit)values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, chainId, credentials, contractAddress, chainRpc, gasPrice.toString(), gasLimit.toString());
        return true;
    }

    public boolean updateContractData(long chainId, String credentials, String contractAddress, String chainRpc, BigInteger gasPrice, BigInteger gasLimit) {
        final String sql = "update contractInfo set credentials=?,contractAddress=?,chainRpc = ?,gasPrice =?,gasLimit =? where chainId = ?";
        jdbcTemplate.update(sql, new Object[]{credentials, contractAddress, chainRpc, gasPrice, gasLimit, chainId});
        return true;
    }

    public ContractPojo showContractInfo(int chainId) {
        String sql = "select a.chainId,a.credentials,a.contractAddress,a.chainRpc,a.gasPrice,a.gasLimit from contractInfo a where chainId = ?;";
        ContractPojo contractPojo = jdbcTemplate.queryForObject(sql, new ContractMapper(), chainId);
        return contractPojo;
    }

}
