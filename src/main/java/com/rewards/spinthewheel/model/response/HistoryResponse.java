package com.rewards.spinthewheel.model.response;


import lombok.Data;

@Data
public class HistoryResponse {
    private String from;
    private String to;
    private String cc;
    private String remainderContent;
    private String createdDate;
    private String status;
}
