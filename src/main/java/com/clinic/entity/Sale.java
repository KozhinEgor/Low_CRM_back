package com.clinic.entity;

import com.clinic.entity.dictionary.Doctor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "salelines", schema = "public")
public class Sale extends BasePaySal {
    @OneToOne
    @JoinColumn(name = "doctor1cid")
    private Doctor doctor;
    @Column(name = "sumwithoutdisc")
    private BigDecimal sumWithoutDisc;
    @Column(name = "quantity")
    private Double quantity;
}
