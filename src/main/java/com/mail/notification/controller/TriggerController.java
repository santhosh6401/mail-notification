package com.mail.notification.controller;


import com.mail.notification.model.entity.AttendanceEntity;
import com.mail.notification.repository.StudentRepository;
import com.mail.notification.model.entity.StudentEntity;
import com.mail.notification.model.request.HistoryRequest;
import com.mail.notification.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trigger")
public class TriggerController {

    @Autowired
    private HistoryController controller;


    @Autowired
    private AttendanceRepository repository;

    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private HistoryController historyController;

    @PostMapping("/send-remainder")
    public List<AttendanceEntity> trigger() {
        List<AttendanceEntity> entityList = repository.findAll();
        List<AttendanceEntity> absentEntity = entityList.stream().filter(attendanceEntity -> attendanceEntity.getStatus().equalsIgnoreCase("0")).collect(Collectors.toList());
        List<AttendanceEntity> absentList = new ArrayList<>();
        for (AttendanceEntity attendanceEntity : absentEntity) {
            long absentCount = absentEntity.stream().filter(absent -> absent.getAdmissionNo().equalsIgnoreCase(attendanceEntity.getAdmissionNo())).count();
            if (absentCount > 2) {
                absentList.add(attendanceEntity);
            }
        }
        if (!removeduplicate(absentList).isEmpty()) {
            for (AttendanceEntity entity : removeduplicate(absentList)) {
                Optional<StudentEntity> entityOptional = studentRepository.findByAdmissionNumber(entity.getAdmissionNo());
                if (entityOptional.isPresent()) {
                    LocalDate today = LocalDate.now();
                    LocalDate lastMonth = LocalDate.now().minusMonths(1);
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate absentDate = LocalDate.parse(entity.getDateTimeTaken(), dateTimeFormatter);
                    if (absentDate.isAfter(lastMonth) && absentDate.isBefore(today)) {
                        HistoryRequest request = new HistoryRequest();
                        request.setTo(entityOptional.get().getOtherName());
                        historyController.create(request);
                    }
                }
            }
        }

        return removeduplicate(absentList);
    }

    private List<AttendanceEntity> removeduplicate(List<AttendanceEntity> absentList) {

        Map<String, AttendanceEntity> map = new HashMap<>();
        for (AttendanceEntity attendance : absentList) {
            String key = attendance.getAdmissionNo();
            if (ObjectUtils.isEmpty(map)) {
                map.put(key, attendance);
            }
            if (!ObjectUtils.isEmpty(map) && map.containsKey(key)) {
                map.put(key, attendance);
            }
        }

        return map.values().stream().toList();
    }

    @GetMapping("/get-student")
    public List<StudentEntity> getStudent() {
        return studentRepository.findAll();
    }

    @Scheduled(cron = "1 * * * * *")
    public void cronJobSch() {
        trigger();
    }
}
