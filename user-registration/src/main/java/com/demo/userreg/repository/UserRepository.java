package com.demo.userreg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.userreg.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByUsername(String username);

	User getByUsername(String userName);

}
