package com.rewards.spinthewheel.controller;


import com.rewards.spinthewheel.model.request.EmailConfigRequest;
import com.rewards.spinthewheel.model.response.EmailConfigResponse;
import com.rewards.spinthewheel.model.response.StatusResponse;
import com.rewards.spinthewheel.service.EmailConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/email-config")
public class EmailConfigController {

    @Autowired
    private EmailConfigService rewardsConfigService;

    @PostMapping("/create")
    public EmailConfigResponse create(@RequestBody EmailConfigRequest request) {
        return rewardsConfigService.save(request);

    }

    @DeleteMapping("/delete")
    public StatusResponse delete(@RequestParam String emailId) {
        return rewardsConfigService.delete(emailId);

    }

    @PutMapping("/put")
    public EmailConfigResponse update(@RequestParam String emailId, @RequestBody EmailConfigRequest request) {
        return rewardsConfigService.update(emailId, request);

    }

    @GetMapping("/get")
    public List<EmailConfigResponse> get() {
        return rewardsConfigService.get();
    }
}
