package com.example.syncdatamarket.utils;

import com.example.syncdatamarket.dao.BlockNumberDao;
import com.example.syncdatamarket.pojo.OrderInfoPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.web3j.abi.datatypes.Type.MAX_BYTE_LENGTH;

@Service
public class asset{


    @Autowired
    private BlockNumberDao blockNumberDao;

    private Decode decode = new Decode();

    private  static  Web3j web3j;
    private  static BigInteger start;
    private static BigInteger end;



    private static final List outputParameters = new ArrayList<TypeReference<Type>>();

    public static final Event COMMITORDER_EVENT = new Event("commitOrderEvent",
            Arrays.asList(new TypeReference<Address>(false) {
            },new TypeReference<DynamicBytes>(false) {
            },new TypeReference<DynamicBytes>(false) {
            },new TypeReference<DynamicBytes>(false) {
            },new TypeReference<DynamicBytes>(false) {
            },new TypeReference<DynamicBytes>(false) {
            },new TypeReference<Uint256>(false) {
            }));

    public static final String COMMITORDER_Topic = EventEncoder.encode(COMMITORDER_EVENT);

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



    public static void  get(){
        web3j = Web3Intence.getInstance("https://api.avax-test.network/ext/bc/C/rpc");
        Credentials credentials = Credentials.create("bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47");
        String number_start = "11039794";
        String number_end = "11040422";
        start  = new BigInteger(number_start);
        end  = new BigInteger(number_end);

        EthFilter filter = new EthFilter( DefaultBlockParameter.valueOf(start),DefaultBlockParameter.valueOf(end),"0x3e7ef740027a963d215Bd24B70BEC62d4a39fded");
        filter.addSingleTopic(COMMITORDER_Topic);
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

                    System.out.println("开始解析"+i);
                    EventValues eventValues = Contract.staticExtractEventParameters(COMMITORDER_EVENT, (Log) logsList.get(i));

                    List<Type> delogResults =   eventValues.getNonIndexedValues();

                    OrderInfoPojo orderInfoPojo = new OrderInfoPojo();
                    orderInfoPojo.setOrderHash("0x"+encodeBytes((BytesType) delogResults.get(1)));
                    orderInfoPojo.setOrder_hash(Hash.sha3("0x"+encodeBytes((BytesType) delogResults.get(1))));
                    System.out.println("0x"+encodeBytes((BytesType) delogResults.get(1)));

                    orderInfoPojo.setAssetData("0x"+encodeBytes((BytesType) delogResults.get(2)));
                    orderInfoPojo.setCallData("0x"+encodeBytes((BytesType) delogResults.get(3)).substring(0, 200));
                    orderInfoPojo.setOrderData("0x"+encodeBytes((BytesType) delogResults.get(4)));
                    orderInfoPojo.setSignature("0x"+encodeBytes((BytesType)delogResults.get(5)).substring(0,130));
                    orderInfoPojo.setTimesTamp( delogResults.get(6).getValue().toString());
                    orderInfoPojo.setTxHash(((Log) logsList.get(i)).getTransactionHash());
                    System.out.println(5);
//                    orderInfoService.insertOrderdataService(orderInfoPojo);  //插入订单所有数据+
//
//                    orderInfoService.insertOrderPrice(orderInfoPojo.getOrder_hash(),decode.decodeOrderData(orderInfoPojo.getOrderData()),decode.decodeAssetData(orderInfoPojo.getAssetData()));//插入订单资产价格
//                    orderInfoService.insertOrderHashInfo(decode.decodeOrderHash(orderInfoPojo.getOrder_hash(),orderInfoPojo.getOrderHash())); //插入订单hash详情数据
//                    orderInfoService.insertOrderState(delogResults.get(0).getValue().toString(),orderInfoPojo.getOrder_hash(),1);
                    System.out.println("交易hash为"+((Log) logsList.get(i)).getTransactionHash());
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("4秒没有产生区块");
            }

        }
//        BigInteger distance = num.subtract(end);
//        if(distance.compareTo(new BigInteger("0")) == 1) {
//            start = end.add(new BigInteger("1"));
//            end = end.add(distance);
////            blockNumberDao.setBlockNumberPojo(start.toString(), end.toString(), 0);
//        }
//        System.out.println("提交订单服务start"+start);
//        System.out.println("提交订单服务end"+end);
//        System.out.println("提交订单服务blockNumber"+ num);
    }

    public static void main(String[] args) {
        get();
    }
}

