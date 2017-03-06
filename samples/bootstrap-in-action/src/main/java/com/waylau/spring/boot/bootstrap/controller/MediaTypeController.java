package com.waylau.spring.boot.bootstrap.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waylau.spring.boot.bootstrap.domain.User;

/**
 * Media Type 控制器.
 * 
 * @author <a href="https://waylau.com">Way Lau</a>
 * @date 2017年2月24日
 */
@RestController
public class MediaTypeController {

	/**
	 * 根据客户端请求的 Content-Type，响应相应的 UserVO 类型.
	 * 
	 * @return
	 */
	@RequestMapping("/user")
	public User getUser() {
		return new User("waylau", 30);
	}

}
