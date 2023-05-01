package com.example.doodle_clone.repo;

import com.example.doodle_clone.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    //@Query("SELECT u FROM user u WHERE u.email = ?1")
    User  findByEmailId(String emailId);
}
