/**
 * 
 */
package com.waylau.spring.boot.thymeleaf.repository;

import java.util.List;

import com.waylau.spring.boot.thymeleaf.vo.UserVO;

/**
 * 用户仓库.
 *
 * @since 1.0.0 2017年3月2日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface UserRepository {
	/**
	 * 新增或者修改用户
	 * @param user
	 * @return
	 */
	UserVO saveOrUpateUser(UserVO user);
	
	/**
	 * 删除用户
	 * @param id
	 */
	void deleteUser(Long id);
	
	/**
	 * 根据用户id获取用户
	 * @param id
	 * @return
	 */
	UserVO getUserById(Long id);
	
	/**
	 * 获取所有用户的列表
	 * @return
	 */
	List<UserVO> listUser();
}
