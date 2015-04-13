package com.dihaw.services.custom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dihaw.entity.UserAttempts;
import com.dihaw.repository.UserAttemptsRepository;
import com.dihaw.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final int MAX_ATTEMPTS = 3;
	
	@Autowired
	UserAttemptsRepository userAttemptsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (name.equals("admin") && password.equals("password")) {
            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
            
            try {

    			userAttemptsRepository.resetFailAttempts(authentication.getName());

    		} catch (BadCredentialsException e) {

    			updateFailAttempts(authentication.getName());
    			throw e;

    		} catch (LockedException e) {

    			String error = "";
    			UserAttempts userAttempts = userAttemptsRepository.findUserAttemptsByUsername(authentication.getName());
    			if (userAttempts != null) {
    				Date lastAttempts = userAttempts.getLastModified();
    				error = "User account is locked! <br><br>Username : " + authentication.getName()
    						+ "<br>Last Attempts : " + lastAttempts;
    			} else {
    				error = e.getMessage();
    			}

    			throw new LockedException(error);
    		}            
            
            return auth;
        } else {
            return null;
        }
		  

	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void updateFailAttempts(String username) {
		UserAttempts user = getUserAttempts(username);
		if (user == null) {
			if (isUserExists(username)) {
				// if no record, insert a new
				userAttemptsRepository.save(user);
			}
		} else {

			if (isUserExists(username)) {
				// update attempts count, +1
				userAttemptsRepository.updateFailAttempts(new Date(), username);
			}

			if (user.getAttempts() + 1 >= MAX_ATTEMPTS) {
				// locked user
				userRepository.changeAccountLocked(user.getId(), 0);
				// throw exception
				throw new LockedException("User Account is locked!");
			}

		}
		
	}
	
	private boolean isUserExists(String username) {
		boolean result = false;

		int count = userRepository.count(username);
		if (count > 0) {
			result = true;
		}
		return result;
	}


	private UserAttempts getUserAttempts(String username) {
		try {

			UserAttempts userAttempts = userAttemptsRepository.findUserAttemptsByUsername(username);
			return userAttempts;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}


}
