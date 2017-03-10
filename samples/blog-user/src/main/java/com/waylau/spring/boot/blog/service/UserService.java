package com.waylau.spring.boot.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.waylau.spring.boot.blog.domain.User;

/**
 * 用户管理 接口.
 * 
 * @since 1.0.0 2017年3月10日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface UserService {
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	User saveUser(User user);
	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	void removeUser(Long id);
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	User updateUser(User user);
	
	/**
	 * 根据id获取用户
	 * @param user
	 * @return
	 */
	User getUserById(Long id);
	
	/**
	 * 获取用户列表
	 * @param user
	 * @return
	 */
	List<User> listUsers();
	
	/**
	 * 根据用户名进行分页查询
	 * @param user
	 * @return
	 */
	Page<User> listUsersByNameAndPage(String name, Pageable pageable);
}
