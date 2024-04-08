package com.booking_doctor_be.controller;

import com.booking_doctor_be.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private IImageService imageService;

    @GetMapping("/house/{houseId}")
    public ResponseEntity<?> getById(@PathVariable int houseId){
        try{
            return ResponseEntity.ok(imageService.findAllByDoctorId(houseId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }
}
