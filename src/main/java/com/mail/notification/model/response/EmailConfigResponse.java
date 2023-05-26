package com.mail.notification.model.response;


import lombok.Data;

@Data
public class EmailConfigResponse {
    private String name;
    private String emailId;
    private String designation;
    private String reminderContent;
    private String status;
}
