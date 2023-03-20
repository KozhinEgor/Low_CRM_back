package com.clinic.entity.dictionary;

import com.clinic.dto.DictionaryDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class BaseDictionary implements Serializable {
    @Id
    private String id;
    @Column
    private String name;

    public DictionaryDto toDto(){
        return new DictionaryDto(id,name);
    }
}
