package com.booking_doctor_be.service;

import com.booking_doctor_be.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IReviewService {
    Page<Review> findAllByDoctorId(int houseId, int page, int size);
    Double avgRating(int houseId);
    List<Review> findAllByAccountId(int accountId);
}
