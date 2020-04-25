package com.gurpreet.repository;

import com.gurpreet.model.BondDetails;

public interface BondRepository {

    BondDetails createBond(BondDetails bondDetails);

    void deleteBond(Integer bondId);

}
