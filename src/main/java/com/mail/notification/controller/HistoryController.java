package com.mail.notification.controller;

import com.mail.notification.model.request.HistoryRequest;
import com.mail.notification.model.response.HistoryResponse;
import com.mail.notification.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/send-notification")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping("/create")
    public HistoryResponse create(@ModelAttribute("request") HistoryRequest request) {
        return historyService.send(request);
    }



    @GetMapping("/get-by-date")
    public List<HistoryResponse> getByDate(@RequestParam String toMailId) {
        return historyService.getByTo(toMailId);
    }


    @GetMapping("/history-all")
    public List<HistoryResponse> getAlls() {
        List<HistoryResponse> responses = historyService.getAll();
        return responses;

    }
}
