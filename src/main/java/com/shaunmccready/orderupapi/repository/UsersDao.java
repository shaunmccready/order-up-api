package com.shaunmccready.orderupapi.repository;

import com.shaunmccready.orderupapi.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersDao extends JpaRepository<Users, String> {

    Optional<Users> findByEmailIgnoreCase(String email);
}
