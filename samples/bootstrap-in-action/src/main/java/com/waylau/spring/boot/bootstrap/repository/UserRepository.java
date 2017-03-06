package com.waylau.spring.boot.bootstrap.repository;

import org.springframework.data.repository.CrudRepository;

import com.waylau.spring.boot.bootstrap.domain.User;

/**
 * 用户仓库.
 *
 * @since 1.0.0 2017年3月2日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface UserRepository extends CrudRepository<User, Long>{
}
