package com.mail.notification.service.implementation;

import com.mail.notification.model.entity.EmailConfigEntity;
import com.mail.notification.model.request.EmailConfigRequest;
import com.mail.notification.model.response.EmailConfigResponse;
import com.mail.notification.model.response.StatusResponse;
import com.mail.notification.repository.EmailConfigRepository;
import com.mail.notification.service.EmailConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmailConfigServiceImplementation implements EmailConfigService {

    @Autowired
    private EmailConfigRepository repository;


    @Override
    public EmailConfigResponse save(EmailConfigRequest request) {

        EmailConfigResponse response = new EmailConfigResponse();

        Optional<EmailConfigEntity> entityOptional = repository.findByEmailId(request.getEmailId());

        if (entityOptional.isPresent()) {
            BeanUtils.copyProperties(entityOptional.get(), response);
            response.setStatus("EMAIL ID ALREADY EXIST");
            return response;
        }

        EmailConfigEntity entity = new EmailConfigEntity();

        BeanUtils.copyProperties(request, entity);

        repository.save(entity);

        BeanUtils.copyProperties(entity, response);

        response.setStatus("EMAIL CONFIG SAVE SUCCESSFULLY...");

        return response;
    }

    @Override
    public StatusResponse delete(String emailId) {

        Optional<EmailConfigEntity> entityOptional = repository.findByEmailId(emailId);

        StatusResponse response = new StatusResponse();

        if (entityOptional.isPresent()) {
            repository.delete(entityOptional.get());
            response.setStatus("EMAIL ID DELETE SUCCESSFULLY ...");
            return response;
        }

        response.setStatus("EMAIL ID  NOT PRESENT");

        return response;
    }

    @Override
    public EmailConfigResponse update(String emailId, EmailConfigRequest request) {

        EmailConfigResponse response = new EmailConfigResponse();

        Optional<EmailConfigEntity> entityOptional = repository.findByEmailId(request.getEmailId());

        if (entityOptional.isEmpty()) {
            response.setStatus("EMAIl ID  NOT EXIST");
            return response;
        }

        EmailConfigEntity entity = entityOptional.get();

        BeanUtils.copyProperties(request, entity);

        repository.save(entity);

        BeanUtils.copyProperties(entity, response);

        response.setStatus("EMAIL ID  UPDATED SUCCESSFULLY");

        return response;
    }

    @Override
    public List<EmailConfigResponse> get() {

        List<EmailConfigEntity> entityList = repository.findAll();

        List<EmailConfigResponse> responsesList = new ArrayList<>();

        if (!entityList.isEmpty()) {
            entityList.forEach(entity -> {

                EmailConfigResponse response = new EmailConfigResponse();
                BeanUtils.copyProperties(entity, response);
                response.setStatus("FETCHED");
                responsesList.add(response);

            });
        }

        return responsesList;
    }
}
