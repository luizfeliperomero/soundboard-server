package com.luizf.soundboard.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository  extends JpaRepository<Plan, Long> {
    @Query(value = "select plan_id from plan where name_ = :name", nativeQuery = true)
    Long findByName(@Param("name") String name);
}
