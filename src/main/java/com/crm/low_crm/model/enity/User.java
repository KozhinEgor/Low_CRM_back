package com.crm.low_crm.model.enity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    /*
    * Telegram nick
    */
    private String name;
    /*
     * Telegram chatID
     */
    private Long chatID;
    /*
    * Активный ли пользователь
     */
    private boolean active;

    private Integer attempt;

}
