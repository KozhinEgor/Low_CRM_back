package com.crm.low_crm.repository;

import com.crm.low_crm.model.enity.HistoryCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoryCall, Long> {
    public HistoryCall findFirstByUid(String uid);


}
