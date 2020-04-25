package com.gurpreet.repository.impl;

import com.gurpreet.model.BondDetails;
import com.gurpreet.repository.BondRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional("bondManagerTransactionManager")
public class BondRepositoryImpl implements BondRepository {

    @PersistenceContext(unitName = "bondManagerEntityManager")
    private EntityManager em;

    @Override
    public BondDetails createBond(BondDetails bondDetails) {
        em.persist(bondDetails);
        return bondDetails;
    }

    @Override
    public void deleteBond(Integer bondId){
        BondDetails bondDetails = em.find(BondDetails.class, bondId);
        em.remove(bondDetails);
    }

}
