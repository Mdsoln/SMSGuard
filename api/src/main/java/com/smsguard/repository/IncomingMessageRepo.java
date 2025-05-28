package com.smsguard.repository;

import com.smsguard.entity.IncomingMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomingMessageRepo extends JpaRepository<IncomingMessage, Long> {
}
