package com.clinic.entity.dictionary;

import com.clinic.entity.dictionary.BaseDictionary;

import javax.persistence.*;

@Entity
@Table(name = "productspecs", schema = "public")
@AttributeOverride(name="id", column=@Column(name="productspec1cid", nullable = false))
public class ProductSpec extends BaseDictionary {

}
