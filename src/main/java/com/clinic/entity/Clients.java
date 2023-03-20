package com.clinic.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "clients", schema = "clinic")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

}
