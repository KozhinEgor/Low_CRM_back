package com.crm.low_crm.model.enity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "address", schema = "public")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_address")
    private String fullAddress;
    @Column(name = "address_from_c")
    private String addressFrom1C;
    @Column(name ="city")
    private String city;
    @Column(name ="street")
    private String street;
    @Column(name = "house")
    private String house;
    @Column(name = "geo_lat")
    private Double geoLat;
    @Column(name = "geo_lon")
    private Double geoLon;
}

