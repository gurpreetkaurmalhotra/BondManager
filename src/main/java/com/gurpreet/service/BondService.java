package com.gurpreet.service;

import com.gurpreet.model.BondDetails;
import com.gurpreet.model.Sales;
import com.gurpreet.request.AddBondRequest;
import com.gurpreet.request.DeleteBondRequest;
import com.gurpreet.request.SellBondRequest;

import java.util.List;

public interface BondService {
    BondDetails addBond(AddBondRequest bondRequest);

    void deleteBond(DeleteBondRequest deleteBondRequest);

    void sellBond(SellBondRequest sellBondRequest);

    List<Sales> getCustomersForBondId(Integer bondId);

    List<Sales> getBondsForCustomer(Integer userId);
}

