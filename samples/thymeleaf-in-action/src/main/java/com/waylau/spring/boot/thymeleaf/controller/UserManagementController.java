package com.waylau.spring.boot.thymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.waylau.spring.boot.thymeleaf.repository.UserRepositoryImpl;
import com.waylau.spring.boot.thymeleaf.vo.UserVO;

/**
 * 用户管理控制器.
 * 
 * @author <a href="https://waylau.com">Way Lau</a>
 * @date 2017年2月26日
 */
@RestController
@RequestMapping("/users")
public class UserManagementController {

	@Autowired 
	private UserRepositoryImpl userRepository;

	/**
	 * 从 用户存储库 获取用户列表
	 * @return
	 */
	private List<UserVO> getUserlist() {
 		return userRepository.listUser();
	}

	/**
	 * 查询所用用户
	 * @return
	 */
	@GetMapping
	public ModelAndView list() {
		ModelMap model = new ModelMap();
		model.put("userList", getUserlist());
		model.put("title", "用户管理");
		return new ModelAndView("users/list", "userModel", model);
	}

	/**
	 * 根据id查询用户
	 * @param message
	 * @return
	 */
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Long id) {
		UserVO user = userRepository.getUserById(id);
		ModelMap model = new ModelMap();
		model.put("user", user);
		model.put("title", "查看用户管理");
		return new ModelAndView("users/view", "userModel", model);
	}

	/**
	 * 获取 form 表单页面
	 * @param user
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView createForm() {
		ModelMap model = new ModelMap();
		model.put("user", new UserVO());
		model.put("title", "创建用户");
		return new ModelAndView("users/form", "userModel", model);
	}

	/**
	 * 新建用户
	 * @param user
	 * @param result
	 * @param redirect
	 * @return
	 */
	@PostMapping
	public ModelAndView create(UserVO user) {
 		user = userRepository.saveUser(user);
		return new ModelAndView("redirect:/users");
	}

	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		userRepository.deleteUser(id);
		
		ModelMap model = new ModelMap();
		model.put("userList", getUserlist());
		model.put("title", "用户管理");
		return new ModelAndView("users/list", "userModel", model);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id) {
		UserVO user = userRepository.getUserById(id);
		
		ModelMap model = new ModelMap();
		model.put("user", user);
		model.put("title", "修改用户");
		return new ModelAndView("users/form", "userModel", model);
	}

}
