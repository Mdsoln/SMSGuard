package com.smsguard.service;

import com.smsguard.constant.Category;
import com.smsguard.dto.SmsRequest;
import com.smsguard.dto.SmsResponse;
import com.smsguard.entity.IncomingMessage;
import com.smsguard.repository.IncomingMessageRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


@Service
public class AIService {

    @Value("${flask.api.url}")
    private String flaskApiUrl;

    private final RestTemplate restTemplate;
    private final IncomingMessageRepo incomingRepo;

    public AIService(RestTemplate restTemplate, IncomingMessageRepo incomingRepo) {
        this.restTemplate = restTemplate;
        this.incomingRepo = incomingRepo;
    }

    public void classify(SmsRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<SmsRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<SmsResponse> response = restTemplate.exchange(
                flaskApiUrl,
                HttpMethod.POST,
                entity,
                SmsResponse.class
        );

        assert response.getBody() != null;
        IncomingMessage incoming = new IncomingMessage();
        incoming.setMobile(request.getMobile());
        incoming.setMessage(request.getMessage());

        if (response.getBody().getPrediction().equals("scam")){
            incoming.setCategory(Category.scam.name());
        }
        else {
            incoming.setCategory(Category.trust.name());
        }
        incomingRepo.save(incoming);

    }

}
