package com.crm.low_crm.repository;

import com.crm.low_crm.model.enity.ParametrReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ParametrReportRepository extends JpaRepository<ParametrReport,Long> {
     ParametrReport findTopById(Long id);
     ParametrReport findTopByNameEn(String name_en);
}
