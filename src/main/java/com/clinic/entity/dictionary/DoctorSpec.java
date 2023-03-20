package com.clinic.entity.dictionary;

import com.clinic.entity.dictionary.BaseDictionary;

import javax.persistence.*;


@Entity
@Table(name = "doctorspecs", schema = "public")
@AttributeOverride(name ="id", column = @Column(name = "doctorspec1cid", nullable = false))
public class DoctorSpec extends BaseDictionary {

}
