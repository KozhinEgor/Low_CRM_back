package com.clinic.entity.dictionary;

import com.clinic.entity.dictionary.BaseDictionary;

import javax.persistence.*;



@Entity
@Table(name = "unit", schema = "public")
@AttributeOverride(name="id", column=@Column(name="unit1cid", nullable = false))
public class Unit extends BaseDictionary {

}
