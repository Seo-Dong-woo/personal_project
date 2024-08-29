package com.sdw.springsecuriy.repository;

import com.sdw.springsecuriy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
