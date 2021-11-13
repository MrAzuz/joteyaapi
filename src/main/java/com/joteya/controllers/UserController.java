package com.joteya.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joteya.dao.UserRepository;
import com.joteya.entities.User;
import com.joteya.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	@GetMapping("/user")
	public User find_User(@RequestParam(value = "id") Long id) {

		User user = new User();
		try {
			user = userService.findUser(id);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return user;
	}

	@PostMapping("/user")
	public User add_User(@RequestBody User user) {
		return userService.addUser(user);

	}
	
	@PutMapping("/user/{id}")
	public User update_User(@PathVariable(value = "id") Long id, @RequestBody User user) {

		return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/user/{id}")
	public void delete_User(@PathVariable(value = "id") Long id) {
		userService.deleteUser(id);
	}

}
