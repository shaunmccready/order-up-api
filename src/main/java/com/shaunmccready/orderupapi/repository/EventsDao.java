package com.shaunmccready.orderupapi.repository;

import com.shaunmccready.orderupapi.domain.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsDao extends JpaRepository<Events, Long> {
}
