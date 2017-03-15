package com.waylau.spring.boot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import com.waylau.spring.boot.security.authentication.JwtLogoutHandler;
import com.waylau.spring.boot.security.authentication.JwtAuthenticationEntryPoint;
import com.waylau.spring.boot.security.authentication.JwtAuthenticationTokenFilter;

/**
 * Spring Security 配置类.
 * 
 * @since 1.0.0 2017年3月8日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public JwtAuthenticationTokenFilter tokenAuthenticationFilter() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
	@Autowired
	UserDetailsService userDetailsService;
	
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
 


	/**
	 * 自定义配置
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
	        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()  //示例显式设置cookieHttpOnly = false,这是必要的，以允许JavaScript读取它
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // 无状态
			.exceptionHandling().authenticationEntryPoint( jwtAuthenticationEntryPoint ).and() // 权限异常处理
			.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/fonts/**","/favicon.ico", "/index","/", "/login","/login-error").permitAll()  // 都可以访问
				//.antMatchers("/users/**")  //.hasRole("ROLE_USER")   // 需要响应的角色才能访问
				//.and()
				.antMatchers("/login/**").permitAll()
				.anyRequest().authenticated().and()
			.formLogin()   //基于 Form 表单登录验证
				.loginPage("/login").failureUrl("/login-error");
       http.addFilterBefore(tokenAuthenticationFilter(), BasicAuthenticationFilter.class);
	}
 
	/**
	 * 认证信息管理
	 * @param authenticationManagerBuilder
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
}
