package com.dihaw.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dihaw.exception.CustomGenericException;


public class ErrorController {}

/*

@Controller
@RequestMapping(ErrorController.CONTROLLER_BASE_PATH)
public class ErrorController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public final static String CONTROLLER_BASE_PATH = "/dihaw";
	
	private final static String ACCESS_DENIED_403_PATH = "/403";
	
	private final static String AUTH_FAILURE_PATH = "/error";
	private final static String AUTH_DENIED_PATH  = "dihaw/auth/denied";
	
	private static String ERROR_VIEW= "error";
	private static String LOGIN_VIEW = "view/login/login";
	
	private static String ERROR_CODE = "errorCode";
	private static String ERROR_MESSAGE = "errorMessage";
	
	@RequestMapping(AUTH_FAILURE_PATH)
	public String showErrorView(Model model){
		
		logger.info("---------- Authentication failure");
		
		model.addAttribute(ERROR_CODE, "Authentication");
		model.addAttribute(ERROR_MESSAGE, "Login Failure!");
		
		return LOGIN_VIEW;
	}
	
	@RequestMapping("/auth" )
	public String authenticationFailureView(Model model, @RequestParam("error") String error){
		
		logger.info("----------> Authentication failure");
		
		model.addAttribute(ERROR_CODE, "Authentication");
		model.addAttribute(ERROR_MESSAGE, error);
		
		return LOGIN_VIEW;
	}
	
	@RequestMapping(AUTH_DENIED_PATH)
	public String authAccessDenied(Model model){
		
		logger.info("---------- Access Denied");
		model.addAttribute(ERROR_CODE, "Authorization");
		model.addAttribute(ERROR_MESSAGE, "Access Denied !");
		
		return ERROR_VIEW;
	}
	
	@RequestMapping("/global")
	public String globalError(Model model){
		
		logger.info("---------- Access Denied");
		model.addAttribute(ERROR_CODE, "Authorization");
		model.addAttribute(ERROR_MESSAGE, "Access Denied !");
		
		return ERROR_VIEW;
	}	
	
	//	403 access denied page
	@RequestMapping(value = ACCESS_DENIED_403_PATH, method = RequestMethod.GET)
	public String accesssDenied(Model model, Principal principal) {
	 
		String username = principal.getName();
 
		if (username != null) {
			model.addAttribute(ERROR_MESSAGE, "Hi <em>" + principal.getName() 
					+ "</em>, you do not have permission to access this page!");
		} else {
			model.addAttribute(ERROR_MESSAGE, 
					"You do not have permission to access this page!");
		}
 
		return ERROR_VIEW;

	}
	
}
*/