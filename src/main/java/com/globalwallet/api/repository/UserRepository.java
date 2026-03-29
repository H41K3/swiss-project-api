package com.globalwallet.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.globalwallet.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
}