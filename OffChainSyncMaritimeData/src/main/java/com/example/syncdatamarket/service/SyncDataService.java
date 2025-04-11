package com.example.syncdatamarket.service;//package com.example.cyerbpopMarket.service;
//
//import com.example.cyerbpopMarket.dao.BlockNumberDao;
//import com.example.cyerbpopMarket.pojo.CoinPojo;
//import com.example.cyerbpopMarket.utils.Web3Intence;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.web3j.abi.EventEncoder;
//import org.web3j.abi.EventValues;
//import org.web3j.abi.TypeReference;
//import org.web3j.abi.datatypes.Address;
//import org.web3j.abi.datatypes.Event;
//import org.web3j.abi.datatypes.Type;
//import org.web3j.abi.datatypes.generated.Uint256;
//import org.web3j.crypto.Credentials;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.DefaultBlockParameter;
//import org.web3j.protocol.core.DefaultBlockParameterName;
//import org.web3j.protocol.core.DefaultBlockParameterNumber;
//import org.web3j.protocol.core.methods.request.EthFilter;
//import org.web3j.protocol.core.methods.response.EthBlockNumber;
//import org.web3j.protocol.core.methods.response.EthLog;
//import org.web3j.protocol.core.methods.response.Log;
//import org.web3j.protocol.http.HttpService;
//import org.web3j.tx.Contract;
//
//import java.io.IOException;
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class SyncDataService {
//
//    public static final Event WITHDRAWCOIN_EVENT = new Event("withdrawCoinEvent",
//            Arrays.asList(new TypeReference<Address>(false) {
//            }, new TypeReference<Uint256>(false) {
//            }, new TypeReference<Uint256>(false) {
//            }));
//
//    public static final String WITHDRAWCOIN_TOPIC = EventEncoder.encode(WITHDRAWCOIN_EVENT);
//
//    @Autowired
//    private BlockNumberDao blockNumberDao;
//
//    private  static BigInteger start;
//    private static BigInteger end;
//
//    @SneakyThrows
//    @Scheduled(fixedDelay = 5 * 1000)
//    public  void  get(){
//        Web3j web3j = Web3Intence.getInstance("https://api.avax-test.network/ext/bc/C/rpc");
//        Credentials credentials = Credentials.create("bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47");
//        String number_start = blockNumberDao.showContractInfo().getStart();
//        String number_end = blockNumberDao.showContractInfo().getEnd();
//        System.out.println(number_start);
//        start  = new BigInteger(number_start);
//        end  = new BigInteger(number_end);
//        EthFilter filter = new EthFilter( DefaultBlockParameter.valueOf(start),DefaultBlockParameter.valueOf(end),"0xAD3Da4A693a7469105a93675A00A789A74461202");
//        filter.addSingleTopic(WITHDRAWCOIN_TOPIC);
//        EthLog logs = null;
//        try {
//            logs = web3j.ethGetLogs(filter).send();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        List<EthLog.LogResult> logsList = logs.getLogs();
//        List<CoinPojo> logsResultList = new ArrayList<>();
//        EthBlockNumber blockNumber = null;
//        try {
//            blockNumber = web3j.ethBlockNumber().send();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        BigInteger num = blockNumber.getBlockNumber();
//
//        if (logsList != null){
//        for (int i =0;i<logsList.size();i++){
//            EventValues eventValues = Contract.staticExtractEventParameters(WITHDRAWCOIN_EVENT, (Log) logsList.get(i));
//            System.out.println("开始解析"+i);
//            List<Type> delogResults =   eventValues.getNonIndexedValues();
//
//            CoinPojo coinPojo =new CoinPojo();
//            coinPojo.setCall((String) delogResults.get(0).getValue());
//            coinPojo.setAmount((BigInteger) delogResults.get(1).getValue());
//            coinPojo.setTimestamp((BigInteger) delogResults.get(2).getValue());
//            logsResultList.add(coinPojo);
//            System.out.println("出现了日志"+coinPojo.getCall());
//            System.out.println("交易hash为"+((Log) logsList.get(i)).getTransactionHash());
//           }
//        }
//        BigInteger distance = num.subtract(end);
//        start = end.add(new BigInteger("1"));
//        end = end.add(distance);
//        blockNumberDao.setBlockNumberPojo(start.toString(),end.toString());
//
//        System.out.println("start"+start);
//        System.out.println("end"+end);
//        System.out.println("blockNumber"+ num);
//    }
//}
//
