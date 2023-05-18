package com.rewards.spinthewheel.service;

import com.rewards.spinthewheel.model.request.HistoryRequest;
import com.rewards.spinthewheel.model.response.HistoryResponse;

import java.util.List;

public interface HistoryService {


    HistoryResponse send(HistoryRequest request);

    List<HistoryResponse> getAll();

    List<HistoryResponse> getByTo(String date);

}
