package com.waylau.spring.boot.elasticsearch.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User value object. 
 * @author <a href="https://waylau.com">Way Lau</a>
 * @date 2017年2月24日
 */
@XmlRootElement // mediatype 转为xml
public class UserVO {

	private String name;
	private int age;

	public UserVO() {
	}

	public UserVO(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
