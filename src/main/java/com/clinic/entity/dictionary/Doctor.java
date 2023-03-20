package com.clinic.entity.dictionary;

import com.clinic.entity.dictionary.BaseDictionary;
import com.clinic.entity.dictionary.DoctorSpec;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "doctors", schema = "public")
@AttributeOverride(name = "id", column = @Column(name = "doctor1cid", nullable = false))
public class Doctor extends BaseDictionary {

    @OneToOne
    @JoinColumn(name = "doctorspec1cid")
    private DoctorSpec spec;
}
