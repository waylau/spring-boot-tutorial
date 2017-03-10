package com.waylau.spring.boot.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.waylau.spring.boot.blog.domain.User;

/**
 * UserService 测试类.
 * 
 * @since 1.0.0 2017年3月10日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase 
public class UserServiceTest {
	
	@Autowired
    private UserService userService;
	
	@Test
    public void testSaveUser() throws Exception {
		User user = userService.saveUser(new User("waylau",29));
        assertThat(user.getName()).isEqualTo("waylau");
        assertThat(user.getAge()).isEqualTo(29);
	}
	
	@Test
    public void testRemoveUser() throws Exception {
		User user = userService.saveUser(new User("老卫",29));
		userService.removeUser(user.getId());
	}
	
	@Test
	public void testRemoveUsersInBatch() throws Exception {
		userService.saveUser(new User("老卫",29));
		userService.removeUsersInBatch(userService.listUsers());
		assertThat(userService.listUsers().size()).isZero();
	}
	
	@Test
    public void testUpdateUser() throws Exception {
		int age = 23;
		User user = userService.saveUser(new User("老卫",43));
		user.setAge(age);
		user = userService.updateUser(user);
		assertThat(user.getAge()).isEqualTo(age);
	}
	
	@Test
    public void testGetUserById() throws Exception {
		int age = 23;
		User user = userService.saveUser(new User("老卫",43));
		user.setAge(age);
		user = userService.updateUser(user);
		assertThat(user.getAge()).isEqualTo(age);
	}
	
	@Test
    public void testListUsers() throws Exception {
		userService.saveUser(new User("老卫",43));
		List<User> users = userService.listUsers();
		assertThat(users.size()).isGreaterThan(0);
	}
	
	@Test
    public void testListUsersByNameAndPage() throws Exception {
		userService.saveUser(new User("老卫",43));
		Pageable pageable = new PageRequest(0, 20000);
		Page<User> users = userService.listUsersByNameLike("%卫%",pageable);
		System.out.println(users.getTotalElements());
		assertThat(users.getTotalElements()).isGreaterThan(0);
	}
}
