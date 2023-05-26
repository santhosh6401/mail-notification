package com.mail.notification.service.implementation;

import com.mail.notification.model.entity.EmailConfigEntity;
import com.mail.notification.model.entity.MailHistory;
import com.mail.notification.model.response.HistoryResponse;
import com.mail.notification.service.HistoryService;
import com.mail.notification.utils.Helper;
import com.mail.notification.model.request.HistoryRequest;
import com.mail.notification.repository.EmailConfigRepository;
import com.mail.notification.repository.EmailHistoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class EmailNotificationService implements HistoryService {


    @Autowired
    private EmailHistoryRepository repository;

    @Autowired
    private EmailConfigRepository emailConfigRepository;

    @Autowired
    private Helper helper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public HistoryResponse send(HistoryRequest request) {

        EmailConfigEntity emailConfigEntity = emailConfigRepository.findAll().get(0);

        HistoryResponse response = new HistoryResponse();

        MailHistory entity = new MailHistory();


        if (ObjectUtils.isEmpty(emailConfigEntity)) {
            response.setStatus("EMAIL CONFIG IS NOT EXIST");
            return response;

        }

        try {

            String content = emailConfigEntity.getReminderContent();
            helper.sendEmail(content, request.getTo(), emailConfigEntity.getEmailId());

            entity.setFrom("msdsaran190@gmail.com");
            entity.setTo(request.getTo());
            entity.setCc(emailConfigEntity.getEmailId());
            entity.setRemainderContent(emailConfigEntity.getReminderContent());
            entity.setCreatedDate(LocalDateTime.now().toString());
            entity.setStatus("MAIL SEND SUCCESSFULLY ");
            response.setStatus("MAIL SEND SUCCESSFULLY ");
            repository.save(entity);

        } catch (Exception e) {

            entity.setFrom("msdsaran190@gmail.com");
            entity.setTo(request.getTo());
            entity.setCc(emailConfigEntity.getEmailId());
            entity.setRemainderContent(emailConfigEntity.getReminderContent());
            entity.setCreatedDate(LocalDateTime.now().toString());
            entity.setStatus("ERROR ON SENDING EMAIL - > " + e.getMessage());
            response.setStatus("ERROR ON SENDING EMAIL - > " + e.getMessage());
            repository.save(entity);
        }

        BeanUtils.copyProperties(entity, response);
        return response;

    }


    @Override
    public List<HistoryResponse> getAll() {

        List<MailHistory> entityList = repository.findAll();

        List<HistoryResponse> responses = new ArrayList<>();

        if (!entityList.isEmpty()) {

            entityList.forEach(entity -> {
                HistoryResponse response = new HistoryResponse();
                BeanUtils.copyProperties(entity, response);

                responses.add(response);
            });

        }

        return responses;
    }

    @Override
    public List<HistoryResponse> getByTo(String to) {

        List<MailHistory> entityList = repository.findByTo(to);

        List<HistoryResponse> responses = new ArrayList<>();

        if (!entityList.isEmpty()) {

            entityList.forEach(entity -> {
                HistoryResponse response = new HistoryResponse();
                BeanUtils.copyProperties(entity, response);

                responses.add(response);
            });

        }

        return responses;
    }

}
