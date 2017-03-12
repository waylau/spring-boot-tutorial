package com.waylau.spring.boot.elasticsearch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waylau.spring.boot.elasticsearch.vo.UserVO;

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
	public UserVO getUser() {
		return new UserVO("waylau", 30);
	}

	@RequestMapping("/users")
	public List<UserVO> getUsers() {
		List<UserVO> list = new ArrayList<>();
		list.add(new UserVO("waylau", 30));
		list.add(new UserVO("老卫", 30));
		return list;
	}
}
