package com.booking_doctor_be.controller;

import com.booking_doctor_be.entity.Booking;
import com.booking_doctor_be.entity.Pet;
import com.booking_doctor_be.repository.IBookingRepo;
import com.booking_doctor_be.repository.IPetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pets")
public class PetController {
    @Autowired
    private IPetRepo petRepo;

    @Autowired
    private IBookingRepo bookingRepo;

    @GetMapping("/{id}")
    public ResponseEntity<List<Pet>> getAll(@PathVariable Integer id){
        List<Pet> pets = petRepo.getPetsByAccount_Id(id);
        return ResponseEntity.ok(pets);
    }
    @GetMapping("/booking/{id}")
    public ResponseEntity<List<Booking>> getBookingPet(@PathVariable Integer id){
        List<Booking> pets = bookingRepo.findAllByPetId(id);
        return ResponseEntity.ok(pets);
    }


    @PostMapping
    public ResponseEntity<Pet> save(@RequestBody Pet pet){
        Pet savedPet = petRepo.save(pet);
        return ResponseEntity.ok(savedPet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> update(@PathVariable Integer id, @RequestBody Pet updatedPet){
        Optional<Pet> petOptional = petRepo.findById(id);
        if (petOptional.isPresent()) {
            Pet existingPet = petOptional.get();
            existingPet.setName(updatedPet.getName());
            existingPet.setBirthday(updatedPet.getBirthday());
            existingPet.setWeight(updatedPet.getWeight());
            Pet savedPet = petRepo.save(existingPet);
            return ResponseEntity.ok(savedPet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Optional<Pet> petOptional = petRepo.findById(id);
        if (petOptional.isPresent()) {
            petRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
