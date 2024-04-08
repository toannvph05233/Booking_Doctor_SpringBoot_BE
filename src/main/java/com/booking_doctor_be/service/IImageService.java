package com.booking_doctor_be.service;

import com.booking_doctor_be.entity.Image;

import java.util.List;

public interface IImageService {
    List<Image> findAllByDoctorId(int houseId);
}
