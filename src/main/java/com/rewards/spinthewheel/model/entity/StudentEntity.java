package com.rewards.spinthewheel.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tblstudents")
public class StudentEntity {

    @Id
    @Column(name = "Id")
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname" )
    private String lastName;

    @Column(name = "othername")
    private String otherName;

    @Column(name = "admissionnumber")
    private String admissionNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "classid")
    private String classId;

    @Column(name = "classarmid")
    private String classArmId;

    @Column(name = "datecreated")
    private String dateCreated;



}
