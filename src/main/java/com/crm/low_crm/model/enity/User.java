package com.crm.low_crm.model.enity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
