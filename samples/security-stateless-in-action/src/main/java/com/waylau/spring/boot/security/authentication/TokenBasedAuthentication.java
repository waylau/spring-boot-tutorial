package com.waylau.spring.boot.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Token Based Authentication.
 * 
 * @since 1.0.0 2017年3月14日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public class TokenBasedAuthentication extends AbstractAuthenticationToken {
 
	private static final long serialVersionUID = 1L;
	private String token;
    private final UserDetails principle;

    public TokenBasedAuthentication( UserDetails principle ) {
        super( principle.getAuthorities() );
        this.principle = principle;
    }

    public String getToken() {
        return token;
    }

    public void setToken( String token ) {
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public UserDetails getPrincipal() {
        return principle;
    }

}
