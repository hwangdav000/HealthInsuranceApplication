package com.synex.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synex.domain.User;



@Service
public interface UserService {

	public List<User> findAll();
	public User save(User u);
	public void deleteUserById(int uId);
	public User findByUserId(int uId);
	public User findByUserName(String userName);
}
