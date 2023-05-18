package com.rewards.spinthewheel.model.request;

import lombok.Data;

@Data
public class EmailConfigRequest {
    private String name;
    private String emailId;
    private String designation;
    private String reminderContent;
}
