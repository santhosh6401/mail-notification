package com.mail.notification.service;


import com.mail.notification.model.request.EmailConfigRequest;
import com.mail.notification.model.response.EmailConfigResponse;
import com.mail.notification.model.response.StatusResponse;

import java.util.List;

public interface EmailConfigService {

    EmailConfigResponse save(EmailConfigRequest request);

    StatusResponse delete(String emailId);

    EmailConfigResponse update(String emailId, EmailConfigRequest request);

    List<EmailConfigResponse> get();
}
