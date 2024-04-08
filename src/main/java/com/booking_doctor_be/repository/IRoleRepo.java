package com.booking_doctor_be.repository;
import com.booking_doctor_be.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepo extends JpaRepository<Role,Integer> {
    Role findByName(String name);
    Role findById(int id);

}

