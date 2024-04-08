package com.booking_doctor_be.service.impl;

import com.booking_doctor_be.dto.DoctorDto;
import com.booking_doctor_be.entity.Doctor;
import com.booking_doctor_be.entity.Image;
import com.booking_doctor_be.repository.IDoctorRepo;
import com.booking_doctor_be.repository.IImageRepo;
import com.booking_doctor_be.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoctorService implements IDoctorService {
    @Autowired
    private IDoctorRepo doctorRepo;
    @Autowired
    private IImageRepo imageRepo;

    @Override
    public Doctor findById(int id) {
        return doctorRepo.findById(id).get();
    }

    @Override
    public Doctor createDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor(doctorDto);
        doctor.setStatus("Đang trống");
        doctor.setCreateAt(LocalDate.now());
        Doctor doctorDB = doctorRepo.save(doctor);
        List<Image> imageList = doctorDto.getImages();
        for (Image image : doctorDto.getImages()) {
            image.setDoctor(doctorDB);
        }
        imageRepo.saveAll(imageList);
        return doctorDB;
    }

    @Override
    public Doctor editDoctor(DoctorDto doctorDto) {
        imageRepo.saveAll(doctorDto.getImages());
        imageRepo.deleteAll(doctorDto.getImagesDelete());
        Doctor doctor = new Doctor(doctorDto);
        doctor.setUpdateAt(LocalDate.now());
        return doctorRepo.save(doctor);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    @Override
    public Page<Doctor> findAllByPriceRange(Pageable pageable, double minPrice, double maxPrice) {
        return doctorRepo.findAllByPriceRange(pageable, minPrice, maxPrice);
    }

    @Override
    public Page<Doctor> findDoctorsByNameAndPriceRange(Pageable pageable, String nameSearch, double minPrice, double maxPrice) {
        return doctorRepo.findDoctorsByNameAndPriceRange(pageable, nameSearch, minPrice, maxPrice);
    }

    @Override
    public Page<Doctor> findDoctorsByNameAndPriceRangeAndLocal(Pageable pageable, String nameSearch, String province, double minPrice, double maxPrice) {
        return doctorRepo.findDoctorsByNameAndPriceRangeAndLocal(pageable, nameSearch, province, minPrice, maxPrice);
    }

    public Doctor updateStatus(int id, String status) {
        Doctor doctor = doctorRepo.findById(id).orElse(null);
        if (doctor != null) {
            doctor.setStatus(status);
            return doctorRepo.save(doctor);
        }
        return null;
    }

    @Override
    public List<Integer> getTopBookingDoctorId() {
        return doctorRepo.getTopBookingDoctorId();
    }

    @Override
    public Page<IDoctorRepo.DoctorInfo> findByOwnerIdAndNameAndStatus(int id, String name, String status, Pageable pageable) {
        return doctorRepo.findByOwnerIdAndNameAndStatus(id, name, status, pageable);
    }

    @Override
    public List<IDoctorRepo.DoctorInfo> findByOwnerId(int ownerId) {
        return doctorRepo.findByOwnerId(ownerId);
    }

    @Override
    public Page<IDoctorRepo.DoctorInfo> findByOwnerId(int ownerId, Pageable pageable) {
        return doctorRepo.findByOwnerId(ownerId, pageable);
    }


    @Override
    public Doctor findByIdAndOwnerId(int doctorId, int ownerId) {
        return doctorRepo.findByIdAndOwnerId(doctorId, ownerId);
    }

}
