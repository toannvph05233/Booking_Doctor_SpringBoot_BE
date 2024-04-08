package com.booking_doctor_be.repository;
import com.booking_doctor_be.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReviewRepo extends JpaRepository<Review,Integer> {
    @Query("SELECT r FROM Review r WHERE r.booking.doctor.id = :houseId")
    Page<Review> findAllByDoctorId(@Param("houseId") int houseId, Pageable pageable);

    @Query("SELECT AVG (r.rating) FROM Review r WHERE r.booking.doctor.id = :houseId")
    Double avgRating(@Param("houseId") int houseId);

    @Query("SELECT r FROM Review r WHERE r.booking.account.id = :accountId")
    List<Review> findAllByAccountId(@Param("accountId") int accountId);
}
