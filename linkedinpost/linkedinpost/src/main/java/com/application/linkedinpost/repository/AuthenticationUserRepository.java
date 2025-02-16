package com.application.linkedinpost.repository;

import com.application.linkedinpost.model.AuthenticationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationUserRepository  extends JpaRepository<AuthenticationUser,Long>
{

    Optional<AuthenticationUser> findByEmail(String email);
}
