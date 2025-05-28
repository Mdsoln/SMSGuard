package com.smsguard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsRequest {
    private String message;
    private String mobile;
}
