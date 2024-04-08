package com.booking_doctor_be.dto;

import com.booking_doctor_be.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAndMessageDto {
    private Account account;
    private int countUnreadMessage;
}
