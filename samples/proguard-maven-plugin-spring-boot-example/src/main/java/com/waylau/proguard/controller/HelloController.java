/**
 * Welcome to https://waylau.com
 */
package com.waylau.proguard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello Controller.
 * 
 * @since 1.0.0 2021年11月11日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
		return getMsg();
	}

	private String getMsg() {
		return "Hello World!";
	}

}