package com.mail.notification.repository;

import com.mail.notification.model.entity.MailHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailHistoryRepository extends MongoRepository<MailHistory, String> {

    List<MailHistory> findByTo(String to);
}
