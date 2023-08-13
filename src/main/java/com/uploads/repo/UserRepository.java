package com.uploads.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.uploads.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}