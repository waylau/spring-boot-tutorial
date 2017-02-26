package com.waylau.spring.boot.thymeleaf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	/**
	 * 模拟用户存储库
	 */
	private Map<Long, UserVO> userRepository = new HashMap<>();

	/**
	 * 从 用户存储库 获取用户列表
	 * @return
	 */
	private List<UserVO> getUserlist() {
		List<UserVO> userList = new ArrayList<>();

		for (Map.Entry<Long, UserVO> entry : userRepository.entrySet()) {
			UserVO user = entry.getValue();
			userList.add(user);
		}

		return userList;
	}

	/**
	 * 查询所用用户
	 * @return
	 */
	@GetMapping
	public ModelAndView list() {
		return new ModelAndView("users/list", "userList", getUserlist());
	}

	/**
	 * 根据id查询用户
	 * @param message
	 * @return
	 */
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Long id) {
		UserVO user = userRepository.get(id);
		return new ModelAndView("users/view", "user", user);
	}

	/**
	 * 获取 form 表单页面
	 * @param user
	 * @return
	 */
	@GetMapping(params = "form")
	public String createForm(@ModelAttribute UserVO user) {
		return "users/form";
	}

	/**
	 * 新建用户
	 * @param user
	 * @param result
	 * @param redirect
	 * @return
	 */
	@PostMapping
	public ModelAndView create(@Valid UserVO user, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("users/form", "formErrors", result.getAllErrors());
		}

		Long key = System.currentTimeMillis(); // 随机生成数字，作为唯一编码
		user.setId(key);
		user = userRepository.put(key, user);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new user");
		return new ModelAndView("redirect:/{user.id}", "user.id", user.getId());
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
		this.userRepository.remove(id);

		return new ModelAndView("users/list", "users", getUserlist());
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") UserVO user) {
		return new ModelAndView("users/form", "user", user);
	}

}
