package com.crm.low_crm.repository;

import com.crm.low_crm.model.dto.HistoryFilter;
import com.crm.low_crm.model.enity.HistoryCall;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Repository
public class HistoryFilterRepository  extends SimpleJpaRepository<HistoryCall, Long> {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final EntityManager entityManager;

    public HistoryFilterRepository(EntityManager entityManager) {
        super(HistoryCall.class,entityManager);
        this.entityManager = entityManager;
    }

    public List<HistoryCall> findByFilter(HistoryFilter filter){
        /*
    //YYYYmmddTHHMMSSZ
    private String start;
    private String end;
    private Period period;
    private TypeCall typeCall;
      today("today"), // – звонки за сегодня
    yesterday("yesterday"), // – звонки за вчера
    this_week("this week"), // – звонки за текущую неделю
    last_week("last week"), // – звонки за прошедшую неделю
    this_month("this month"), // – звонки за текущий месяц
    last_month("last month"); // – звонки за прошедший месяц*/
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<HistoryCall> query = builder.createQuery(HistoryCall.class);
        Root<HistoryCall> historyCallRoot = query.from(HistoryCall.class);
        List<Predicate> predicates = new ArrayList<>();

        if(filter.getStart() != null){
            predicates.add(builder.greaterThan(historyCallRoot.get("start"), ZonedDateTime.parse(filter.getStart())));
        }
        if(filter.getEnd() != null){
            predicates.add(builder.lessThan(historyCallRoot.get("start"), ZonedDateTime.parse(filter.getEnd())));
        }
        if(filter.getPeriod() != null){
            switch (filter.getPeriod()){
                case today:
                    predicates.add(builder.equal( builder.function("DATE", LocalDate.class, historyCallRoot.get("start")),
                            LocalDate.now())
                    );
                    break;
                case yesterday:
                    predicates.add(builder.equal( builder.function("DATE", LocalDate.class, historyCallRoot.get("start")),
                            LocalDate.now().minusDays(1))
                    );
                    break;
                case this_week:
                    predicates.add(builder.greaterThan(builder.function("DATE", LocalDate.class, historyCallRoot.get("start")), LocalDate.now().minusDays(LocalDate.now().getDayOfWeek().ordinal())));
                    predicates.add(builder.lessThan(builder.function("DATE", LocalDate.class, historyCallRoot.get("start")), LocalDate.now()));
                    break;
                case this_month:
                    predicates.add(builder.greaterThan(builder.function("DATE", LocalDate.class, historyCallRoot.get("start")), LocalDate.now().minusDays(LocalDate.now().getDayOfMonth())));
                    predicates.add(builder.lessThan(builder.function("DATE", LocalDate.class, historyCallRoot.get("start")), LocalDate.now()));
                    break;
                case last_week:
                    predicates.add(builder.greaterThan(builder.function("DATE", LocalDate.class, historyCallRoot.get("start")), LocalDate.now().minusDays(7).minusDays(LocalDate.now().getDayOfMonth())));
                    predicates.add(builder.lessThan(builder.function("DATE", LocalDate.class, historyCallRoot.get("start")), LocalDate.now().minusDays(7)));
                    break;
                case last_month:
                    LocalDate lastMonth = LocalDate.now().minusMonths(1);
                    predicates.add(builder.greaterThan(builder.function("DATE", LocalDate.class, historyCallRoot.get("start")), lastMonth.minusDays(lastMonth.getDayOfMonth())));
                    predicates.add(builder.lessThan(builder.function("DATE", LocalDate.class, historyCallRoot.get("start")), lastMonth));
                    break;
            }

        }
        query.select(historyCallRoot).where( predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
