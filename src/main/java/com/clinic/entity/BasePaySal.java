package com.clinic.entity;

import com.clinic.entity.dictionary.Unit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class BasePaySal {
    @Id
    @Column(name = "doc1cid", nullable = false)
    private String id;
    @Column(name = "docdate")
    private LocalDateTime docDate;
    @Column(name = "docnt")
    private String docNt;
    @Column(name = "linenumber")
    private String lineNumber;
    @OneToOne
    @JoinColumn(name = "unit1cid")
    private Unit unit;
    @OneToOne
    @JoinColumn(name = "client1cid")
    private Clients client;
    @Column(name = "sum")
    private BigDecimal sum;
}
