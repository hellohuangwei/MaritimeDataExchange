package com.example.syncdatamarket.mapper;



import com.example.syncdatamarket.pojo.ContractPojo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现RowMapper接口，返回User对象
 */
@Component
public class ContractMapper implements RowMapper<ContractPojo> {

    @Override
    public ContractPojo mapRow(ResultSet resultSet, int i) throws SQLException {

        int chainId = resultSet.getInt("chainId");
        String credentials = resultSet.getString("credentials");
        String contractAddress = resultSet.getString("contractAddress");
        String chainRpc = resultSet.getString("chainRpc");
        String gasPrice = resultSet.getString("gasPrice");
        String gasLimit = resultSet.getString("gasLimit");
        ContractPojo contractPojo = new ContractPojo();

        contractPojo.setChainId(chainId);
        contractPojo.setCredentials(credentials);
        contractPojo.setContractAddress(contractAddress);
        contractPojo.setChainRpc(chainRpc);
        contractPojo.setGasPrice(new BigInteger(gasPrice));
        contractPojo.setGasLimit(new BigInteger(gasLimit));

        return contractPojo;
    }
}