package com.waylau.spring.boot.bootstrap.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User 实体
 * 
 * @since 1.0.0 2017年3月5日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Entity  // 实体
@XmlRootElement // MediaType 转为 XML
public class User implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	@Id  // 主键
    @GeneratedValue(strategy=GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识

	@Column(nullable = false) // 映射为字段，值不能为空
 	private String name;
	
	@Column(nullable = false)
	private Integer age;

	protected User() {  // JPA 的规范要求无参构造函数；设为 protected 防止直接使用 
	}

	public User(String name, Integer age) {
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
