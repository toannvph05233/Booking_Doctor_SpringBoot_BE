package com.booking_doctor_be.service.impl;

import com.booking_doctor_be.service.IImageService;
import com.booking_doctor_be.entity.Image;
import com.booking_doctor_be.repository.IImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepo imageRepo;
    @Override
    public List<Image> findAllByDoctorId(int houseId) {
        return imageRepo.findAllByDoctorId(houseId);
    }
}
