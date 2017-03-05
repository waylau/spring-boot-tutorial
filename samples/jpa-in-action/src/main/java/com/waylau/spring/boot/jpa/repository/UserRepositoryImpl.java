package com.waylau.spring.boot.jpa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.waylau.spring.boot.jpa.domain.User;
 

/**
 * 用户资源库.
 *
 * @version 1.0.0 2017年3月2日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
	
	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<Long, User>();
 
	public UserRepositoryImpl(){
		User user = new User("Way Lau",30);
 
		this.saveOrUpateUser(user);
	}
	
	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.thymeleaf.repository.UserRepository#saveUser(com.waylau.spring.boot.thymeleaf.vo.UserVO)
	 */
	@Override
	public User saveOrUpateUser(User user) {
		Long id = user.getId();
		if (id <= 0) {
			id = counter.incrementAndGet();
			user.setId(id);
		}
		this.userMap.put(id, user);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.thymeleaf.repository.UserRepository#deleteUser(java.lang.Long)
	 */
	@Override
	public void deleteUser(Long id) {
		this.userMap.remove(id);
	}

	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.thymeleaf.repository.UserRepository#getUserById(java.lang.Long)
	 */
	@Override
	public User getUserById(Long id) {
		return this.userMap.get(id);
	}

	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.thymeleaf.repository.UserRepository#listUser()
	 */
	@Override
	public List<User> listUser() {
		return new ArrayList<User>(this.userMap.values());
	}

}
