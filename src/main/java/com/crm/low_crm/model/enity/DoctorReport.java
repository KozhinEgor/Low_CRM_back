package com.crm.low_crm.model.enity;

import com.clinic.entity.dictionary.Doctor;
import com.clinic.entity.dictionary.DoctorSpec;
import com.crm.low_crm.model.enumerate.TypeReport;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "reports", schema = "public")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class DoctorReport extends BaseReport{
    /*



first_visit
second_visit
services

*/

//    @Type(type = "jsonb") // See (2)
//    @Column(name = "address", columnDefinition = "jsonb")
//    private  address;

}
