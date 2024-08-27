package com.synex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);
	User findByUserId(int id);

}
