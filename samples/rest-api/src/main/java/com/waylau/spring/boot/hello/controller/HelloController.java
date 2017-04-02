package com.waylau.spring.boot.hello.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello World 控制器
 * @author <a href="https://waylau.com">Way Lau</a> 
 * @date 2017年1月26日
 */
@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
	    return "Hello World! Welcome to visit waylau.com!";
	}
	
	@PutMapping("/hello")
	public String helloPut() {
	    return "Hello World! Put!";
	}
	
	@DeleteMapping("/hello")
	public String helloDelete() {
	    return "Hello World! Delete!";
	}
}
