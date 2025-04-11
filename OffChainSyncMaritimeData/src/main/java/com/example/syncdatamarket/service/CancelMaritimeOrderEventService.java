package com.example.syncdatamarket.service;

import com.example.syncdatamarket.dao.BlockNumberDao;
import com.example.syncdatamarket.pojo.CancelOrderPojo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.BytesType;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;
import com.example.syncdatamarket.utils.Web3Intence;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.web3j.abi.datatypes.Type.MAX_BYTE_LENGTH;

@Service
public class CancelMaritimeOrderEventService {

    @Autowired
    private MaritimeOrderInfoService orderInfoService;

    @Autowired
    private BlockNumberDao blockNumberDao;

    private  static  Web3j web3j;
    private  static BigInteger start;
    private static BigInteger end;

    private static final List outputParameters = new ArrayList<TypeReference<Type>>();

    public static final Event CANCELORDER_EVENT = new Event("cancelOrderEvent",
            Arrays.asList(new TypeReference<Address>(false) {
            }, new TypeReference<Bytes32>(false) {}, new TypeReference<Uint256>(false) {}));

    public static final String CANCELORDER_Topic = EventEncoder.encode(CANCELORDER_EVENT);

    static String encodeBytes(BytesType bytesType) {
        byte[] value = bytesType.getValue();
        int length = value.length;
        int mod = length % MAX_BYTE_LENGTH;

        byte[] dest;
        if (mod != 0) {
            int padding = MAX_BYTE_LENGTH - mod;
            dest = new byte[length + padding];
            System.arraycopy(value, 0, dest, 0, length);
        } else {
            dest = value;
        }
        return Numeric.toHexStringNoPrefix(dest);
    }

    @SneakyThrows
    @Scheduled(fixedDelay = 5  * 1000)
    public  void  get(){
//        web3j = Web3Intence.getInstance("https://bsc-dataseed1.defibit.io");
//        web3j = Web3Intence.getInstance("https://api.avax-test.network/ext/bc/C/rpc");
        web3j = Web3Intence.getInstance("https://rpc.coinex.net/");
        Credentials credentials = Credentials.create("bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47");

        String number_start = blockNumberDao.showContractInfo(1).getStart();
        String number_end = blockNumberDao.showContractInfo(1).getEnd();

        start  = new BigInteger(number_start);
        end  = new BigInteger(number_end);
        EthFilter filter = new EthFilter( DefaultBlockParameter.valueOf(start),DefaultBlockParameter.valueOf(end),"0x4c08248Ff58bd9EE1f43d758236ea96A851c72bA");
//        EthFilter filter = new EthFilter( DefaultBlockParameter.valueOf(start),DefaultBlockParameter.valueOf(end),"0x3e7ef740027a963d215Bd24B70BEC62d4a39fded");

        filter.addSingleTopic(CANCELORDER_Topic);
        EthLog logs = null;
        try {
            logs = web3j.ethGetLogs(filter).send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<EthLog.LogResult> logsList = logs.getLogs();

        EthBlockNumber blockNumber = null;
        try {
            blockNumber = web3j.ethBlockNumber().send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BigInteger num = blockNumber.getBlockNumber();

        if (logsList != null){
            try {
                for (int i =0;i<logsList.size();i++){
                    System.out.println("开始解析取消订单"+i);
                    EventValues eventValues = Contract.staticExtractEventParameters(CANCELORDER_EVENT, (Log) logsList.get(i));
                    List<Type> delogResults =   eventValues.getNonIndexedValues();
                    System.out.println(encodeBytes((BytesType) delogResults.get(1)));

                    CancelOrderPojo cancelOrderPojo = new CancelOrderPojo();
                    cancelOrderPojo.setCalladdress(delogResults.get(0).getValue().toString());
                    cancelOrderPojo.setOrder_hash( "0x" + encodeBytes( (BytesType) delogResults.get(1)));
                    cancelOrderPojo.setTimestamp(delogResults.get(2).getValue().toString());
                    cancelOrderPojo.setTxHash(((Log) logsList.get(i)).getTransactionHash());

                    orderInfoService.insertOrderCancelData(cancelOrderPojo);
                    orderInfoService.updateOrderState(cancelOrderPojo.getOrder_hash(),0);
                    System.out.println("交易hash为"+((Log) logsList.get(i)).getTransactionHash());
                }
            }catch (Exception e){
                System.out.println("4秒没有产生区块");
            }
        }
        BigInteger distance = num.subtract(end);

        if(distance.compareTo(new BigInteger("2047")) == 1) {
            start = end.add(new BigInteger("1"));
            end = start.add(new BigInteger("2047"));
            blockNumberDao.setBlockNumberPojo(start.toString(), end.toString(), 1);
        }

        if(distance.compareTo(new BigInteger("0")) == 1 && distance.compareTo(new BigInteger("2048")) == -1 ) {
            start = end.add(new BigInteger("1"));
            end = end.add(distance);
            blockNumberDao.setBlockNumberPojo(start.toString(), end.toString(), 1);
        }
//
//        System.out.println("取消订单服务start"+start);
//        System.out.println("取消订单服务end"+end);
//        System.out.println("取消订单服务blockNumber"+ num);
    }

}

