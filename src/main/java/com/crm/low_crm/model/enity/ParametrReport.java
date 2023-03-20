package com.crm.low_crm.model.enity;

import com.crm.low_crm.model.dto.FilterReport;
import com.crm.low_crm.model.dto.FromTable;
import com.crm.low_crm.model.dto.GroupReport;
import com.crm.low_crm.model.dto.ShowValues;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Entity
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Table(name = "parametr_report", schema = "public")
public class ParametrReport  implements Serializable {
    /*"dateStart":"Date",
	"dateFinish":"Date",
	"typeReport":"string",
	"from":[{"name": "string", "main":"boolean", "joinTable":"string", "columnParent":"string", "columChild":"string", "joinType":"string"}],
    "group":{"name": "string", "func":"string"},
	"filter":[{"name": "string", "typeFilter": "string", "value": ["string", "string"]}],
	"showValues":[{"name":"string", "func": "string"}]*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Имя поиска
    private String name;
    //Имя на английском
    @Column(unique = true)
    private String nameEn;
    //Таблицы для формирования отчета
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<FromTable> from;
    //Группировка по полю
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<GroupReport> group;
    //Фильтры
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<FilterReport> filter;
    //Отображаемые значения
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<ShowValues> showValues;
}
