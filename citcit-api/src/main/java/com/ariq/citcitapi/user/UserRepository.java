package com.ariq.citcitapi.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariq.citcitapi.user.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
}
