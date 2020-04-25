package com.gurpreet.validator;

import com.gurpreet.request.AddBondRequest;
import com.gurpreet.request.DeleteBondRequest;
import com.gurpreet.request.SellBondRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class BondValidator {

    public static boolean validBond(AddBondRequest request) throws com.gurpreet.exception.BondManagerException {
        return StringUtils.isNotEmpty(request.getBondDescription()) && StringUtils.isNotEmpty(request.getBondName())
                && request.getUserId() != null && request.getPrice() !=null
                && request.getProfitPredictionPercentage() != null
                && request.getReturnPercentage() != null;
    }

    public static boolean validDeleteBondRequest(DeleteBondRequest request) throws com.gurpreet.exception.BondManagerException {
        return request.getUserId() != null && request.getBondId() != null;
    }

    public static boolean validSellBondRequest(SellBondRequest request) throws com.gurpreet.exception.BondManagerException {
        return request.getBondId() != null && request.getSellerId() != null;
    }


}
