package com.booking_doctor_be.repository;
import com.booking_doctor_be.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IImageRepo extends JpaRepository<Image, Integer> {
    List<Image> findAllByDoctorId(int doctorId);
}
