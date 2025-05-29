package com.smsguard.repository;

import com.smsguard.entity.IncomingMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomingMessageRepo extends JpaRepository<IncomingMessage, Long> {
    List<IncomingMessage> findByCategory(String category);
}
