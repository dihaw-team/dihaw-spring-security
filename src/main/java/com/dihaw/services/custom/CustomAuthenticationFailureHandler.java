package com.dihaw.services.custom;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.transaction.annotation.Transactional;

import com.dihaw.controller.exception.UserNotFoundException;
import com.dihaw.entity.User;
import com.dihaw.entity.UserAttempts;
import com.dihaw.repository.UserAttemptsRepository;
import com.dihaw.services.UserService;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
	
    String customException = "";
    private Integer maxLoginFailureCount;
    private String defaultFailureUrl;
    Date lastModified = new Date();

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserAttemptsRepository userAttemptsRepository;

    public void setMaxLoginFailureCount(Integer maxLoginFailureCount) {
        this.maxLoginFailureCount = maxLoginFailureCount;
    }

    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
    }
    
    public void setDefaultFailureUrl(String defaultFailureUrl) {
        this.defaultFailureUrl = defaultFailureUrl;
    }

    @Override
    @Transactional
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException{
        String username = obtainUsername(request);
        
        logger.info("-----onAuthenticationFailure");
        
        User user = userService.getUserByUsername(username);
        
        if (maxLoginFailureCount == null) {
            maxLoginFailureCount = 3;
        }
        if(defaultFailureUrl == null){
        	defaultFailureUrl = "/login?code=";
        }
        	
        logger.info("-----maxLoginFailureCount: "+maxLoginFailureCount);
        logger.info("-----accountNonLocked: "+user.getAccountNonLocked());
        
        if (user != null && user.getAccountNonLocked() == 0 ) {
        	UserAttempts userAttempts = userAttemptsRepository.findUserAttemptsByUsername(username);
        	
        	if(userAttempts!= null)
        		lastModified = userAttempts.getLastModified();
        	
        }
        

        if (user != null && user.getAccountNonLocked() == 1 ) {
            
        	Integer failureCount = 1;
        	
        	logger.info("-0--> failureCount: "+failureCount);
        	
            UserAttempts userAttempts = userAttemptsRepository.findUserAttemptsByUsername(username);
            if(userAttempts != null){
            	
            	failureCount = userAttempts.getAttempts();
            
	            failureCount++;
	            
	            if (failureCount >= maxLoginFailureCount) {
	                user.setAccountNonLocked(0);
	                userAttemptsRepository.updateLoginFailureCount(username, failureCount, new Date());
	                try {
						userService.updateUser(user);
					} catch (UserNotFoundException e) {
						
						logger.info("---> UserNotFoundException: "+e.getMessage());
					}
	            } else if(failureCount < maxLoginFailureCount && failureCount >0) {
	            	
	            	userAttemptsRepository.updateLoginFailureCount(username, failureCount, new Date());
	            }else if(failureCount == 0){
	            	
	            	UserAttempts ua = new UserAttempts(username, failureCount, new Date());
	            	userAttemptsRepository.save(ua);
	            }
            
            
            }else{
            	UserAttempts ua = new UserAttempts(username, failureCount, new Date());
            	userAttemptsRepository.save(ua);
            }
            
            logger.info("-1--> failureCount: "+failureCount);

        }
        
        if (exception instanceof DisabledException) {
        	customException = "disabled";
        } else if (exception instanceof AccountExpiredException) {
        	customException = "accountExpired";
        } else if (exception instanceof LockedException) {
        	customException = "locked";
        } else if (exception instanceof CredentialsExpiredException) {
        	customException = "credentialsExpired";
        }else if (exception instanceof BadCredentialsException) {
        	customException = "badCredentials";
        } else {
        	customException = "others";
        }
        
        response.sendRedirect(request.getContextPath() + defaultFailureUrl +customException);
        
    }
}
