package com.smsguard.controller;

import com.smsguard.dto.SmsRequest;
import com.smsguard.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/smsguard-tz")
@RequiredArgsConstructor
public class ReceiveSmsController {
    private final AIService aiService;

    @PostMapping("/incoming-sms")
    public ResponseEntity<?> receiveSms(@RequestBody SmsRequest sms) {
        aiService.classify(sms);
        return null;
    }

}
