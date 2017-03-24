package com.waylau.spring.boot.blog.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User 实体
 * 
 * @since 1.0.0 2017年3月5日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@XmlRootElement // MediaType 转为 XML
public class User implements Serializable{
 
	private static final long serialVersionUID = 1L;
 
	private Long id; // 用户的唯一标识

 	private String name;
	
	private Integer age;

	protected User() {  // JPA 的规范要求无参构造函数；设为 protected 防止直接使用 
	}

	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	
	public User(Long id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
    public String toString() {
        return String.format(
                "User[id=%d, name='%s', age='%d']",
                id, name, age);
    }
}
