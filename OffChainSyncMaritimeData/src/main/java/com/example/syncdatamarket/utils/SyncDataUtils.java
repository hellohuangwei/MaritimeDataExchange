package com.example.syncdatamarket.utils;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class SyncDataUtils {

    private static Web3j web3j = null;
    public static final Event WITHDRAWCOIN_EVENT = new Event("withdrawCoinEvent",
            Arrays.asList(new TypeReference<Address>(false) {
            }, new TypeReference<Uint256>(false) {
            }, new TypeReference<Uint256>(false) {
            }));

    public static final String WITHDRAWCOIN_TOPIC = EventEncoder.encode(WITHDRAWCOIN_EVENT);


    private static volatile SyncDataUtils instance = null;
    private SyncDataUtils(){}
    public static SyncDataUtils getInstance() {
        if (instance == null) {
            synchronized (SyncDataUtils.class) {
                if (instance == null) {
                    instance = new SyncDataUtils();
                }
            }
        }
        return instance;
    }

    public void start(String chainUrl,BigInteger start, BigInteger end, String address) throws IOException {
        Web3j web3j =  initWeb3j(chainUrl);
        EthFilter filter = new EthFilter(new DefaultBlockParameterNumber(start), new DefaultBlockParameterNumber(end), address);
        EthLog logs = web3j.ethGetLogs(filter).send();

        Log log = (Log) logs.getLogs().get(0);
        EventValues eventValues = Contract.staticExtractEventParameters(WITHDRAWCOIN_EVENT,log);
        List<Type> list3 = eventValues.getNonIndexedValues();
        System.out.println(list3.get(2).getValue());
    }

    public static Web3j initWeb3j(String chainUrl) {
        if (null == web3j) {
            return Web3j.build(new HttpService((chainUrl)));
        }
        return web3j;
    }
}