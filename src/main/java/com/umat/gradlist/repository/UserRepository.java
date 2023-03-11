package com.umat.gradlist.repository;

import com.umat.gradlist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    public User findByEmail(String email);
}
