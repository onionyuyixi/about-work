package com.example.aboutwork.txhandle.repository;

import com.example.aboutwork.txhandle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
}
