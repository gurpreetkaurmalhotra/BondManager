package com.gurpreet.repository.impl;

import com.gurpreet.model.User;
import com.gurpreet.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional("bondManagerTransactionManager")
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext(unitName = "bondManagerEntityManager")
    private EntityManager em;

    @Override
    public User createUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User getUserByUserId(Integer userId){

        return em.find(User.class, userId);
    }

}
