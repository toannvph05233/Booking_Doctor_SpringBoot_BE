package com.booking_doctor_be.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SearchRequest {
    private String nameSearch;
    private String status;
    private LocalDateTime selectedDateStart;
    private LocalDateTime selectedDateEnd;
}
