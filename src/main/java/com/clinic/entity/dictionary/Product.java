package com.clinic.entity.dictionary;

import com.clinic.entity.dictionary.BaseDictionary;
import com.clinic.entity.dictionary.ProductSpec;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "products", schema = "public")
@AttributeOverride(name = "id", column = @Column(name="product1cid", nullable = false))
public class Product extends BaseDictionary {
    @OneToOne
    @JoinColumn(name = "productspec1cid")
    private ProductSpec spec;
    @Column(name = "productkind")
    private String kind;
}
