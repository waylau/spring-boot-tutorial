/**
 * 
 */
package com.waylau.spring.boot.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.waylau.spring.boot.blog.domain.User;
import com.waylau.spring.boot.blog.repository.UserRepository;

/**
 * 
 * 
 * @since 1.0.0 2017年3月10日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void removeUser(Long id) {
		userRepository.delete(id);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public List<User> listUsers() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> listUsersByNameAndPage(String name, Pageable pageable) {
		return userRepository.findByName(name, pageable);
	}

}
