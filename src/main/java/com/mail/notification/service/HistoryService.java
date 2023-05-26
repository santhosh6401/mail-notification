package com.mail.notification.service;

import com.mail.notification.model.response.HistoryResponse;
import com.mail.notification.model.request.HistoryRequest;

import java.util.List;

public interface HistoryService {


    HistoryResponse send(HistoryRequest request);

    List<HistoryResponse> getAll();

    List<HistoryResponse> getByTo(String date);

}
