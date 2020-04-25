package com.gurpreet.repository;

import com.gurpreet.model.Sales;

import java.util.List;

public interface SaleRepository {

    Sales createSale(Sales sales);

    List<Sales> getSalesByBondId(Integer bondId);

    List<Sales> getSalesByUserId(Integer userId);

}
