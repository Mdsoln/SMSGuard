package com.smsguard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "incoming_sms")
@Getter
@Setter
public class IncomingMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incomingId;

    @Column(name = "from")
    private String mobile;
    @Column(name = "message")
    private String message;
    @Column(name = "category")
    private String category;
    @Column(name = "notify_status")
    private boolean notify;

    public IncomingMessage() {
    }

    public IncomingMessage(String mobile, String message, String category) {
        this.mobile = mobile;
        this.message = message;
        this.category = category;
    }

    private boolean isScam(){
        return Objects.equals(this.category, "scam");
    }
}
