package com.cigna.benefit.repository;

import com.cigna.benefit.model.BenefitEvents;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BenefitEventsRepository extends MongoRepository<BenefitEvents, String> {
    @Query("{'id':?0, 'isRead':'false'}")
    List<BenefitEvents> findByReadFalse(String id);
}
