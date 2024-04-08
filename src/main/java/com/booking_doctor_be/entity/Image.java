package com.booking_doctor_be.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    @ManyToOne
    private Doctor doctor;
    public Image(String url, Doctor doctor){
        this.url = url;
        this.doctor = doctor;
    }

    public Image(int id, String url){
        this.url = url;
        this.id = id;
    }
}
