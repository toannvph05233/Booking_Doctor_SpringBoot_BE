package com.booking_doctor_be.repository;

import com.booking_doctor_be.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPetRepo extends JpaRepository<Pet, Integer> {
    List<Pet> getPetsByAccount_Id(int accountId);
}
