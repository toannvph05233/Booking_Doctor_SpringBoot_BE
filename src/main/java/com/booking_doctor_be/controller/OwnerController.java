package com.booking_doctor_be.controller;

import com.booking_doctor_be.dto.DoctorDto;
import com.booking_doctor_be.entity.Booking;
import com.booking_doctor_be.entity.Doctor;
import com.booking_doctor_be.service.IBookingService;
import com.booking_doctor_be.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/owners")
public class OwnerController {
    @Autowired
    private IDoctorService houseService;
    @Autowired
    IBookingService bookingService;

    @PostMapping("/create-house")
    public ResponseEntity<?> createDoctor(@RequestBody DoctorDto houseDto) {
        try {
            Doctor doctor = houseService.createDoctor(houseDto);
            if (doctor == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Create house fail!");
            else
                return ResponseEntity.ok(doctor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }

    @PostMapping("/edit-house")
    public ResponseEntity<?> editDoctor(@RequestBody DoctorDto houseDto) {
        try {
            Doctor doctor = houseService.editDoctor(houseDto);
            if (doctor == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Edit house fail!");
            else
                return ResponseEntity.ok(doctor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }

    @PostMapping("/cancel-booking/{id}")
    public ResponseEntity<?> cancelBookingOwner(@PathVariable int id, @RequestBody Map<String, String> requestBody) {
        try {
            String message = requestBody.get("message");
            Booking booking = bookingService.findById(id);
            String toEmail = booking.getAccount().getEmail();
            String contentTitle = "Chủ nhà đã hủy lịch thuê nhà";
            bookingService.cancelBooking(booking, toEmail, contentTitle, message);
            return ResponseEntity.ok("Hủy thành công");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }
}
