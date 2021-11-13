package com.joteya.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joteya.dao.UserRepository;
import com.joteya.entities.Product;
import com.joteya.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		User u = new User();
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setPasswordConfirm(user.getPasswordConfirm());
		u.setRoles(user.getRoles());
		return userRepository.save(u);
	}

	@Override
	public User updateUser(Long id, User user) {
		
		User u = findUser(id);
		
		if(u != null) {

		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setPasswordConfirm(user.getPasswordConfirm());
		u.setRoles(user.getRoles());
		return userRepository.save(u);
		}else {
			return null;
		}
	}

	@Override
	public void deleteUser(Long id) {
		User u = findUser(id);
		userRepository.delete(u);
	}

	@Override
	public User findUser(Long id) {
		Optional<User> u = userRepository.findById(id);

		if (u == null)
			throw new RuntimeException("can not find user with ID = " + id);

		return u.get();
	}

}
