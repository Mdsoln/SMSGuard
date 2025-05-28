package com.smsguard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsResponse {
    private String prediction;
    private String message;
}
