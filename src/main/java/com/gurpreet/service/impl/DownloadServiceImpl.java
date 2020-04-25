package com.gurpreet.service.impl;

import com.gurpreet.model.Sales;
import com.gurpreet.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Service
@Slf4j
public class DownloadServiceImpl implements DownloadService {

    public static final int FETCH_SIZE = 500;

    @PersistenceContext(unitName = "bondManagerEntityManager")
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public void downloadCsvV3(String startDate, String endDate, HttpServletResponse response){

        response.addHeader("Content-Type", "application/csv");
        response.addHeader("Content-Disposition", "attachment; filename=sales.csv");
        response.setCharacterEncoding("UTF-8");

        try {
            PrintWriter out = response.getWriter();

            StatelessSession session = ((Session) em.getDelegate()).getSessionFactory().openStatelessSession();

            String queryString = "SELECT s from Sales s";

            if(StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
                queryString += " WHERE s.createdDate >= :startDate AND s.createdDate >= :endDate";
            }

            queryString += " ORDER BY s.createdDate asc";

            Query query = session.createQuery(queryString);

            if(StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
                query.setParameter("startDate", startDate);
                query.setParameter("endDate", startDate);
            }

            query.setFetchSize(FETCH_SIZE);
            query.setReadOnly(true);
            query.setLockMode("a", LockMode.NONE);

            out.write(addHeaderInCsv());
            out.write("\n");

            ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
            while (results.next()) {
                Sales sales = (Sales) results.get(0);
                String line = salesToCSV(sales);
                out.write(line);
                out.write("\n");
            }

            out.flush();
            out.close();
            results.close();
            session.close();

        } catch (Exception e) {
            log.info("Exception occurred " + e.getMessage(), e);
        }
    }

    private String addHeaderInCsv() {
        return String.join(",", "Bond Id", "Buyer Id",
                "Seller Id", "Sale Type", "Date");
    }

    private String salesToCSV(Sales sales) {
        return String.join(",", "" + sales.getBondId(), "" + sales.getBuyerId(),
                "" + sales.getSellerId(), "" + sales.getSaleType(), "" + sales.getCreatedDate());
    }
}