package com.rewards.spinthewheel.repository;

import com.rewards.spinthewheel.model.entity.EmailConfigEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmailConfigRepository extends MongoRepository<EmailConfigEntity, Integer> {

    //Optional<RewardsConfigEntity> findByRewardNo(int rewardsNo);

    Optional<EmailConfigEntity> findByEmailId(String emailId);
}
