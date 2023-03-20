package com.clinic.entity;



import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "paymentlines", schema = "public")
public class Payment extends BasePaySal {
    @Column(name = "paymenttype")
    private String paymentType;
}
