package com.waylau.spring.boot.security.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User 实体
 * 
 * @since 1.0.0 2017年3月5日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Entity  // 实体
@XmlRootElement // MediaType 转为 XML
public class User implements UserDetails, Serializable{
 
	private static final long serialVersionUID = 1L;
	
	@Id  // 主键
    @GeneratedValue(strategy=GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识

	@Column(nullable = false) // 映射为字段，值不能为空
 	private String name;
	
	@Column(nullable = false)
	private Integer age;

    @Column(name = "username")  // 用户账号，用户登录时的唯一标识
    private String username;

    //@JsonIgnore
    @Column(name = "password") // 登录时密码
    private String password;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authority> authorities;
    
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
                "User[id=%d,username='%s', name='%s', age='%d']",
                id, name, age);
    }
 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
    
	@Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
