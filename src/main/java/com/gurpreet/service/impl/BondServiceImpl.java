package com.gurpreet.service.impl;

import com.gurpreet.enums.ErrorCode;
import com.gurpreet.enums.SaleType;
import com.gurpreet.enums.UserType;
import com.gurpreet.exception.BondManagerException;
import com.gurpreet.model.BondDetails;
import com.gurpreet.model.Sales;
import com.gurpreet.model.User;
import com.gurpreet.repository.BondRepository;
import com.gurpreet.repository.SaleRepository;
import com.gurpreet.repository.UserRepository;
import com.gurpreet.request.AddBondRequest;
import com.gurpreet.request.DeleteBondRequest;
import com.gurpreet.request.SellBondRequest;
import com.gurpreet.service.BondService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BondServiceImpl implements BondService {

    @Autowired
    BondRepository bondRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SaleRepository saleRepository;


    @Override
    public BondDetails addBond(AddBondRequest addBondRequest) {

        User user = userRepository.getUserByUserId(addBondRequest.getUserId());

        if (user == null) {
            throw new BondManagerException(ErrorCode.BAD_REQUEST.getMessage(), ErrorCode.BAD_REQUEST);
        }

        if (user.getUserType().equals(UserType.ADMIN)) {

            BondDetails bondDetails = BondDetails.builder().bondDescription(addBondRequest.getBondDescription())
                    .bondName(addBondRequest.getBondName()).price(addBondRequest.getPrice())
                    .profitPrediction(addBondRequest.getProfitPredictionPercentage()).returnPercentage(addBondRequest.getReturnPercentage()).build();

            return bondRepository.createBond(bondDetails);

        } else {
            throw new BondManagerException(ErrorCode.AUTHORIZATION_FAIL.getMessage(), ErrorCode.AUTHORIZATION_FAIL);
        }

    }

    public void deleteBond(DeleteBondRequest deleteBondRequest) {

        User user = userRepository.getUserByUserId(deleteBondRequest.getUserId());

        if (user == null) {
            throw new BondManagerException(ErrorCode.BAD_REQUEST.getMessage(), ErrorCode.BAD_REQUEST);
        }

        if (user.getUserType().equals(UserType.ADMIN)) {

            List<Sales> salesList = saleRepository.getSalesByBondId(deleteBondRequest.getBondId());

            if(salesList == null){

                bondRepository.deleteBond(deleteBondRequest.getBondId());
            }
        }
    }


    public void sellBond(SellBondRequest sellBondRequest){

        User buyerUser = userRepository.getUserByUserId(sellBondRequest.getBuyerId());
        User sellerUser = userRepository.getUserByUserId(sellBondRequest.getSellerId());

        SaleType saleType = buyerUser.getUserType().equals(UserType.CUSTOMER) ? SaleType.CustomerToCustomer : SaleType.SalesPersonToCustomer;
        Sales sales = Sales.builder().bondId(sellBondRequest.getBondId()).buyerId(sellBondRequest.getBuyerId())
                .sellerId(sellBondRequest.getSellerId()).createdDate(new Date(System.currentTimeMillis()))
                .updatedDate(new Date(System.currentTimeMillis())).saleType(saleType).build();

        saleRepository.createSale(sales);
    }


    public List<Sales> getCustomersForBondId(Integer bondId){

        List<Sales> salesList = saleRepository.getSalesByBondId(bondId);
        return salesList;
    }

    public List<Sales> getBondsForCustomer(Integer userId){

        List<Sales> salesList = saleRepository.getSalesByUserId(userId);
        return salesList;
    }

}