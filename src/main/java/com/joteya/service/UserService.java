package com.joteya.service;

import com.joteya.entities.User;

public interface UserService {
	public User addUser(User user);
	public User updateUser(Long id, User user);
	public void deleteUser(Long id);
	public User findUser(Long id);

}
