package com.rewards.spinthewheel.model.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "email-history")
public class MailHistory {
    @Id
    private String id;
    private String from;
    private String to;
    private String cc;
    private String remainderContent;
    private String createdDate;
    private String status;
}
