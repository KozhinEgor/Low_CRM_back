package com.crm.low_crm.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ReportRepository {
    private final EntityManager entityManager;

    public ReportRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List test(String columns, String from, String order, String group) {
       /* String columns = "component.name";
        String from = "computernetwork.component";
        String order = null;
        String group = null;*/
        String query = "SELECT " + columns + " FROM " + from + (order != null? " order by " + order: "") + (group != null? " group by " + group : "");
        return entityManager.createNativeQuery(query).getResultList();
    }
}
