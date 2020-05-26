package com.shaunmccready.orderupapi.repository;

import com.shaunmccready.orderupapi.domain.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsDao extends JpaRepository<Items, Long> {
}
