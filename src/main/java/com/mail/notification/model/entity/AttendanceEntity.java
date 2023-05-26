package com.mail.notification.model.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tblattendance")
public class AttendanceEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "admissionno")
    private String admissionNo;

    @Column(name = "classid" )
    private String classId;

    @Column(name = "classarmid")
    private String classArmId;

    @Column(name = "sessiontermid")
    private String sessionTermId;

    @Column(name = "status")
    private String status;

    @Column(name = "datetimetaken")
    private String dateTimeTaken;

}
