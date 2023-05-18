package com.rewards.spinthewheel.repository;

import com.rewards.spinthewheel.model.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, String> {
}
