package com.booking_doctor_be.controller;

import com.booking_doctor_be.entity.Doctor;
import com.booking_doctor_be.repository.IDoctorRepo;
import com.booking_doctor_be.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private IDoctorService doctorService;

    @GetMapping("/search")
    public Page<Doctor> findDoctorsByPriceRange(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "12") int size,
            @RequestParam(value = "nameSearch", defaultValue = "") String nameSearch,
            @RequestParam(value = "province", defaultValue = "") String province,
            @RequestParam(value = "minPrice") double minPrice,
            @RequestParam(value = "maxPrice", required = false) double maxPrice) {
        province = province.replace("_", " ");
        if (maxPrice == 0) {
            maxPrice = Double.MAX_VALUE;
        }
        Pageable pageable = PageRequest.of(page, size);
        if (nameSearch.trim().equals("") && province.equals("")) {
            return doctorService.findAllByPriceRange(pageable, minPrice, maxPrice);
        } else if (province.equals("")) {
            return doctorService.findDoctorsByNameAndPriceRange(pageable, nameSearch, minPrice, maxPrice);
        } else {
            return doctorService.findDoctorsByNameAndPriceRangeAndLocal(pageable, nameSearch, province, minPrice, maxPrice);
        }
    }


    @GetMapping("/{houseId}")
    public ResponseEntity<?> getById(@PathVariable int houseId) {
        try {
            return ResponseEntity.ok(doctorService.findById(houseId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }

    @GetMapping("/{doctorId}/{ownerId}")
    public ResponseEntity<?> getById(@PathVariable int doctorId, @PathVariable int ownerId) {
        try {
            return ResponseEntity.ok(doctorService.findByIdAndOwnerId(doctorId, ownerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }


    @GetMapping("/owner/search/{ownerId}")
    public Page<IDoctorRepo.DoctorInfo> findByOwnerIdAndNameContains(@PathVariable int ownerId,
                                                                    @RequestParam("name") String name,
                                                                    @RequestParam("status") String status,
                                                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                                                    @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return doctorService.findByOwnerIdAndNameAndStatus(ownerId, name, status, pageable);
    }

    @GetMapping("/owner/revenue/{ownerId}")
    public List<IDoctorRepo.DoctorInfo> findByOwnerId(@PathVariable int ownerId) {
        return doctorService.findByOwnerId(ownerId);
    }

    @GetMapping("/owner/listDoctor/{ownerId}")
    public Page<IDoctorRepo.DoctorInfo> findByOwnerIdAndNameContains(@PathVariable int ownerId,
                                                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                                                    @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return doctorService.findByOwnerId(ownerId, pageable);
    }


    @PutMapping("/owner/{doctorId}")
    public Doctor updateStageStatus(@PathVariable int doctorId, @RequestParam("status") String status) {
        return doctorService.updateStatus(doctorId, status);
    }

    @GetMapping("/top5")
    public List<Doctor> getTopBookingDoctor() {
        List<Integer> doctorId = doctorService.getTopBookingDoctorId();
        List<Doctor> doctors = new ArrayList<>();
        for (int i = 0; i < doctorId.size(); i++) {
            doctors.add(doctorService.findById(doctorId.get(i)));
            if (i == 5) break;
        }
        return doctors;
    }

}