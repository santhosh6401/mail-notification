package com.rewards.spinthewheel.model.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "email-config")
public class EmailConfigEntity {

    @Id
    private String id;
    private String name;
    private String emailId;
    private String designation;
    private String reminderContent;

}
