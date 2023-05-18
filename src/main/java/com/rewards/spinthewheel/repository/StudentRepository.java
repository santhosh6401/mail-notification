package com.rewards.spinthewheel.repository;

import com.rewards.spinthewheel.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    Optional<StudentEntity> findByAdmissionNumber(String otherName);
}
