/**
 * 
 */
package com.waylau.spring.boot.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.waylau.spring.boot.security.authentication.TokenBasedAuthentication;
import com.waylau.spring.boot.security.authentication.TokenHelper;
 

/**
 * 基于 token 认证授权的过滤器
 * 
 * @since 1.0.0 2017年3月14日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String AUTH_HEADER;

    @Value("${jwt.cookie}")
    private String AUTH_COOKIE;

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    UserDetailsService userDetailsService;
    
    private String getToken( HttpServletRequest request ) {
        /**
         *  Getting the token from Cookie store
         */
        Cookie authCookie = getCookieValueByName( request, AUTH_COOKIE );
        if ( authCookie != null ) {
            return authCookie.getValue();
        }
        /**
         *  Getting the token from Authentication header
         *  e.g Bearer your_token
         */
        String authHeader = request.getHeader(AUTH_HEADER);
        if ( authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    /**
     * Find a specific HTTP cookie in a request.
     *
     * @param request
     *            The HTTP request object.
     * @param name
     *            The cookie name to look for.
     * @return The cookie, or <code>null</code> if not found.
     */
    protected Cookie getCookieValueByName(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null;
        }
        for (int i = 0; i < request.getCookies().length; i++) {
            if (request.getCookies()[i].getName().equals(name)) {
                return request.getCookies()[i];
            }
        }
        return null;
    }
	/* (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
        String authToken = getToken( request );
        // get username from token
        String username = tokenHelper.getUsernameFromToken( authToken );
        if ( username != null ) {
            // get user
            UserDetails userDetails = userDetailsService.loadUserByUsername( username );
            // create authentication
            TokenBasedAuthentication authentication = new TokenBasedAuthentication( userDetails );

            authentication.setToken( authToken );
            authentication.setAuthenticated( true );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
	}

}
