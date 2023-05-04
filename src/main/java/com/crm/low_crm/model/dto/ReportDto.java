package com.crm.low_crm.model.dto;

import com.base.IdentifiableDTO;
import com.clinic.entity.dictionary.Doctor;
import com.clinic.entity.dictionary.DoctorSpec;
import com.crm.low_crm.model.enity.User;
import com.crm.low_crm.model.enumerate.TypeReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ReportDto implements IdentifiableDTO {

    public UUID id;

    public LocalDateTime createdDate;

    public TypeReport type;

    public User creater;

    public LocalDateTime dateStart;

    public LocalDateTime dateFinish;

    public DoctorSpec doctorSpec;

    public Doctor doctor;

    public Integer precent;

    public Boolean done;
}

