package com.waylau.spring.boot.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.waylau.spring.boot.blog.domain.User;

/**
 * 用户控制器.
 * 
 * @author <a href="https://waylau.com">Way Lau</a>
 * @date 2017年2月26日
 */
@RestController
@RequestMapping("/users")
public class UserController {
	
	/**
	 * 从 用户存储库 获取用户列表
	 * @return
	 */
	private List<User> getUserlist() {
		List<User>  list = new ArrayList<>();
		
		list.add(new User("asfs",21));
		list.add(new User("asf11s",2221));
 		return list;
	}

	/**
	 * 查询所用用户
	 * @return
	 */
	@GetMapping
	public ModelAndView list() {
 
		return new ModelAndView("users/list");
	}
 
 

}
