package com.gurpreet.repository.impl;

import com.gurpreet.model.Sales;
import com.gurpreet.repository.SaleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional("bondManagerTransactionManager")
public class SaleRepositoryImpl implements SaleRepository{


    @PersistenceContext(unitName = "bondManagerEntityManager")
    private EntityManager em;

    @Override
    public Sales createSale(Sales sales){

        em.persist(sales);
        return sales;
    }

    @Override
    public List<Sales> getSalesByBondId(Integer bondId) {
        Query query = em.createQuery("from Sales s where s.bondId="+ bondId);
        List<Sales> salesList= query.getResultList();
        return salesList;
    }

    @Override
    public List<Sales> getSalesByUserId(Integer userId) {

        Query query = em.createQuery("from Sales s where s.buyerId="+ userId);
        List<Sales> salesList= query.getResultList();
        return salesList;
    }

}
