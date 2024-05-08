package com.booking_doctor_be.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date birthday;
    private double weight;
    private String name;
    @ManyToOne
    private Account account;
}
