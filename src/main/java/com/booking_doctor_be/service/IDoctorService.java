package com.booking_doctor_be.service;

import com.booking_doctor_be.dto.DoctorDto;
import com.booking_doctor_be.entity.Doctor;
import com.booking_doctor_be.repository.IDoctorRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDoctorService {
    Doctor findById(int id);

    Doctor createDoctor(DoctorDto houseDto);

    Page<IDoctorRepo.DoctorInfo> findByOwnerIdAndNameAndStatus(int id, String name, String status, Pageable pageable);

    List<IDoctorRepo.DoctorInfo> findByOwnerId(int ownerId);

    Page<IDoctorRepo.DoctorInfo> findByOwnerId(int ownerId, Pageable pageable);

    Doctor editDoctor(DoctorDto houseDto);

    Doctor saveDoctor(Doctor doctor);

    Page<Doctor> findAllByPriceRange(Pageable pageable, double minPrice, double maxPrice);

    Page<Doctor> findDoctorsByNameAndPriceRange(Pageable pageable, String nameSearch, double minPrice, double maxPrice);

    Page<Doctor> findDoctorsByNameAndPriceRangeAndLocal(Pageable pageable, String nameSearch, String province, double minPrice, double maxPrice);

    Doctor findByIdAndOwnerId(int houseId, int ownerId);

    Doctor updateStatus(int id, String status);

    List<Integer> getTopBookingDoctorId();
}
