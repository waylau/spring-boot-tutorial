package com.waylau.spring.boot.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.waylau.spring.boot.blog.domain.Authority;
import com.waylau.spring.boot.blog.domain.User;
import com.waylau.spring.boot.blog.service.AuthorityService;
import com.waylau.spring.boot.blog.service.UserService;
 

/**
 * 用户控制器.
 * 
 * @author <a href="https://waylau.com">Way Lau</a>
 * @date 2017年2月26日
 */
@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")  // 指定角色权限才能操作方法
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService  authorityService;

	/**
	 * 查询所用用户
	 * @return
	 */
	@GetMapping
	public ModelAndView list(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="name",required=false,defaultValue="") String name,
			Model model) {
	 
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<User> page = userService.listUsersByNameLike(name, pageable);
		List<User> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("userList", list);
		return new ModelAndView(async==true?"users/list :: #mainContainerRepleace":"users/list", "userModel", model);
	}

	/**
	 * 获取 form 表单页面
	 * @param user
	 * @return
	 */
	@GetMapping("/add")
	public ModelAndView createForm(Model model) {
		model.addAttribute("user", new User(null, null, null, null));
		return new ModelAndView("users/add", "userModel", model);
	}

	/**
	 * 新建用户
	 * @param user
	 * @param result
	 * @param redirect
	 * @return
	 */
	@PostMapping
	public ResponseEntity<User> create(User user, Long authorityId) {
		List<Authority> authorities = new ArrayList<>();
		authorities.add(authorityService.getAuthorityById(authorityId));
		user.setAuthorities(authorities);
		userService.saveUser(user);
		return ResponseEntity.ok().body( user);
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id, Model model) {
		userService.removeUser(id);
		return  ResponseEntity.ok().body( "删除成功！");
	}
	
	/**
	 * 获取修改用户的界面，及数据
	 * @param user
	 * @return
	 */
	@GetMapping(value = "edit/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return new ModelAndView("users/edit", "userModel", model);
	}
}
