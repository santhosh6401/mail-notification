package com.rewards.spinthewheel.service;


import com.rewards.spinthewheel.model.request.EmailConfigRequest;
import com.rewards.spinthewheel.model.response.EmailConfigResponse;
import com.rewards.spinthewheel.model.response.StatusResponse;

import java.util.List;

public interface EmailConfigService {

    EmailConfigResponse save(EmailConfigRequest request);

    StatusResponse delete(String emailId);

    EmailConfigResponse update(String emailId, EmailConfigRequest request);

    List<EmailConfigResponse> get();
}
