package com.smsguard.controller;

import com.smsguard.constant.Actions;
import com.smsguard.dto.SmsRequest;
import com.smsguard.service.AIService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/smsguard-tz")
@RequiredArgsConstructor
public class ReceiveSmsController {
    private final AIService aiService;


    @PostMapping(value = "/receive", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> receiveSms(
            @RequestParam(name = "action") String action,
            @RequestParam(name = "from",required =false) String from,
            @RequestParam(name = "message",required = false) String message,
            @RequestParam(name = "messageType",required = false) String messageType
    )throws JSONException {

        Map<String,Object> response = new HashMap<>();

        if (Actions.ACTION_INCOMING.equals(action)){
            SmsRequest request = new SmsRequest();
            request.setMobile(from);
            request.setMessage(message);
            aiService.classify(request);
        } else if (Actions.ACTION_OUTGOING.equals(action)) {
            JSONArray eventJson = aiService.handleOutgoingSms();
            response.put("events",eventJson);
        }else {
            response.put("Error",aiService.error());
        }
        return new ResponseEntity<>(new JSONObject(response).toString(), HttpStatus.OK);
    }

}
