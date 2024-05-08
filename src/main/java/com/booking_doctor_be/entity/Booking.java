package com.booking_doctor_be.entity;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime startTime;
    private double total;
    private String status;
    private String hour;
    private String content;
    private String result;
    private LocalDateTime create_at;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Account account;
    @ManyToOne
    private Pet pet;
}
