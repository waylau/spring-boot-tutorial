package com.waylau.spring.boot.security.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtLogoutHandler implements LogoutHandler {

    @Value("${app.user_cookie}")
    private String USER_COOKIE;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // Erase cookies
        Cookie authCookie = new Cookie( TOKEN_COOKIE, null ); // Not necessary, but saves bandwidth.
        authCookie.setPath( "/" );
        authCookie.setMaxAge( 0 ); // Don't set to -1 or it will become a session cookie!

        Cookie userCookie = new Cookie( USER_COOKIE, null );
        userCookie.setPath( "/" );
        userCookie.setMaxAge( 0 );

        response.addCookie(authCookie);
        response.addCookie(userCookie);
    }
}
