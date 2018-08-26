package com.example.scheid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scheid.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	long count();
	
}
