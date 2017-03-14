package com.waylau.spring.boot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.waylau.spring.boot.security.authentication.AuthenticationFailureHandler;
import com.waylau.spring.boot.security.authentication.AuthenticationSuccessHandler;
import com.waylau.spring.boot.security.authentication.JwtLogoutHandler;
import com.waylau.spring.boot.security.filter.TokenAuthenticationFilter;

/**
 * Spring Security 配置类.
 * 
 * @since 1.0.0 2017年3月8日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	TokenAuthenticationFilter tokenAuthenticationFilter;
	
	@Autowired
	UserDetailsService userDetailsService;
	
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    
    @Autowired
    private JwtLogoutHandler jwtLogoutHandler;
	/**
	 * 自定义配置
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // 无状态
			//.exceptionHandling().authenticationEntryPoint( restAuthenticationEntryPoint ).and() // 权限异常处理
			.addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class)
			.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll()  // 虽都可以访问
				.antMatchers("/users/**").hasRole("USER")   // 需要响应的角色才能访问
				.and()
			.formLogin()   //基于 Form 表单登录验证
				.loginPage("/login").failureUrl("/login-error")
				.successHandler(authenticationSuccessHandler)
		        .failureHandler(authenticationFailureHandler).and()
		    .logout()
		        .addLogoutHandler(jwtLogoutHandler)
		        .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))) ;
	}
 
	/**
	 * 认证信息管理
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
}
