package com.crm.low_crm.model.enity;

import com.base.Identifiable;
import com.clinic.entity.dictionary.Doctor;
import com.clinic.entity.dictionary.DoctorSpec;
import com.crm.low_crm.model.enumerate.TypeReport;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Accessors(chain = true)
@Data
public abstract class BaseReport implements Identifiable {

    @Id
    public UUID id;
    @Column(name = "created_date")
    public LocalDateTime createdDate;
    @Column(name = "report_type")
    public TypeReport type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="creater")
    public User creater;
    @Column(name = "date_start")
    public LocalDateTime dateStart;
    @Column(name = "date_finish")
    public LocalDateTime dateFinish;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doctor_spec")
    public DoctorSpec doctorSpec;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doctor")
    public Doctor doctor;
    @Column(name = "precent")
    public Integer precent;
    @Column(name = "done")
    public Boolean done;
}
