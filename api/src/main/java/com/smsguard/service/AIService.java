package com.smsguard.service;

import com.smsguard.constant.Actions;
import com.smsguard.constant.Category;
import com.smsguard.dto.SmsRequest;
import com.smsguard.dto.SmsResponse;
import com.smsguard.entity.IncomingMessage;
import com.smsguard.repository.IncomingMessageRepo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;


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
        incoming.setNotify(false);
        incomingRepo.save(incoming);

    }

    public JSONArray handleOutgoingSms() throws JSONException {
        List<IncomingMessage> outgoing = incomingRepo.findByCategory(Category.scam.name());
        JSONArray eventsArray = new JSONArray();
        for (IncomingMessage incoming : outgoing){
            JSONObject eventObject = getJsonObject(incoming);
            eventsArray.put(eventObject);
            incoming.setNotify(true);
            incomingRepo.save(incoming);
        }
        return eventsArray;
    }

    private static JSONObject getJsonObject(IncomingMessage outgoing) throws JSONException {
        JSONObject eventObject = new JSONObject();
        eventObject.put("event", Actions.EVENT_SEND);
        JSONArray messagesArray = new JSONArray();
        JSONObject messageObject = new JSONObject();

        messageObject.put("id", String.valueOf(outgoing.getIncomingId()));
        messageObject.put("to", outgoing.getMobile());// TODO: 5/30/25 sending the scam message to the Spam and Block section of the normal messaging app
        messageObject.put("message", outgoing.getMessage());
        messagesArray.put(messageObject);

        eventObject.put("messages", messagesArray);
        return eventObject;
    }

    public JSONArray error() throws JSONException {
        JSONArray event = new JSONArray();
        JSONObject eventObject = new JSONObject();

        eventObject.put("message","unsupported action!!!");
        event.put(eventObject);
        return event;
    }

}
