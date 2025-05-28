package com.smsguard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "scam_sms")
@Getter
@Setter
public class ScamMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scamId;

    @Column(name = "mobile")
    private String mobile;
    @Column(name = "message")
    private String message;

    public ScamMessage() {
    }

    public ScamMessage(String mobile, String message) {
        this.mobile = mobile;
        this.message = message;
    }

}
