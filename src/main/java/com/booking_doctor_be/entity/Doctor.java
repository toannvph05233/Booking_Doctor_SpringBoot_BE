package com.booking_doctor_be.entity;

import com.booking_doctor_be.dto.DoctorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String province;
    private String district;
    @Column(columnDefinition = "TEXT")
    private String service;
    private String phone;
    @Column(columnDefinition = "TEXT")
    private String description;
    private double price;
    private int sale;
    @Column(columnDefinition = "TEXT")
    private String thumbnail;
    private String status;
    private LocalDate createAt;
    private LocalDate updateAt;
    @ManyToOne
    private Account owner;

    public Doctor(DoctorDto houseDto) {
        this.id = houseDto.getId();
        this.name = houseDto.getName();
        this.address = houseDto.getAddress();
        this.province = houseDto.getProvince();
        this.district = houseDto.getDistrict();
        this.description = houseDto.getDescription();
        this.price = houseDto.getPrice();
        this.sale = houseDto.getSale();
        this.thumbnail = houseDto.getThumbnail();
        this.owner = houseDto.getOwner();
        this.status = houseDto.getStatus();
    }


    public Doctor(String name, String address, String province, String district, String service, String phone, String description, double price, int sale, String thumbnail, String status, LocalDate createAt, LocalDate updateAt, Account owner) {
        this.name = name;
        this.address = address;
        this.province = province;
        this.district = district;
        this.service = service;
        this.phone = phone;
        this.description = description;
        this.price = price;
        this.sale = sale;
        this.thumbnail = thumbnail;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.owner = owner;
    }
}
