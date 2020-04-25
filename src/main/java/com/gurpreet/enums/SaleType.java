package com.gurpreet.enums;

public enum  SaleType {

    SalesPersonToCustomer(1),
    CustomerToCustomer(2);

    private int saleType;

    SaleType (int saleType) {
        this.saleType = saleType;
    }

    public int getSaleType() {
        return saleType;
    }

    public void setSaleType(int saleType) {
        this.saleType = saleType;
    }
}
