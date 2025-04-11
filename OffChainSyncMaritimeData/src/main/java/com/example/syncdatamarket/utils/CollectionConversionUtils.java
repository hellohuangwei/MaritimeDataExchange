package com.example.syncdatamarket.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CollectionConversionUtils {

    public List<BigInteger> StringToBigIntegerList(String[] strs){
        List<BigInteger> res = new ArrayList<>();
        for (int i=0;i<strs.length;i++){
            res.add(new BigInteger(strs[i]));
        }
        return res;
    }


}
