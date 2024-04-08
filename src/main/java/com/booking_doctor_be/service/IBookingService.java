package com.booking_doctor_be.service;

import com.booking_doctor_be.dto.query.SpendingDto;
import com.booking_doctor_be.entity.Booking;
import com.booking_doctor_be.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;

public interface IBookingService {
    List<Booking> findAllByDoctorIdAndStatus(int houseId);

    List<Booking> getAllBooking();

    void save(Booking booking);

    void deleteById(int id);

    Booking bookingDoctor(Booking booking);

    Booking waitOwnerConfirmBooking(int bookingId);

    void cancelBooking(Booking booking, String toEmail, String contentTitle, String message) throws MessagingException;

    List<Booking> getAll();

    Page<Booking> getByIdAccount(Pageable pageable, int idAccount);

    Booking findById(int id);

    List<Double> getDailyRevenueByOwnerAndWeek(int ownerId, int month, int year, int startDay, int endDay);

    Page<Booking> findByDoctorAndStartTimeAndEndTimeAndStatus(int ownerId, String nameSearch, String status, LocalDateTime startTime, Pageable pageable);
    Review createReview(Review review);

    SpendingDto getSpendingUser(@Param("idAccount") int idAccount);

    Page<Booking> getRentalHistoryIdAccount(Pageable pageable, int idAccount , String houseName , LocalDateTime startTime, String status );

}
