package com.booking_doctor_be.dto;

import com.booking_doctor_be.entity.Account;
import com.booking_doctor_be.entity.Doctor;
import com.booking_doctor_be.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OwnerDto {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private String province;
    private String district;
    private String ward;
    private String frontside;
    private String backside;
    private String status;
    private Account account;
    private String avatar;
    private String description;
    private String service;
    private double price;
    private int sale;

    public Owner getOwner() {
        return new Owner(id, firstname, lastname, email, phone, address, province, district, ward, frontside,
                backside, status, account, avatar);
    }

    public Doctor getDoctor(){
        return new Doctor(0,lastname + " " + firstname,address,province,district,service,phone,description,price,sale,avatar,"Đang hoạt động", LocalDate.now(), LocalDate.now(),account );
    }
}
