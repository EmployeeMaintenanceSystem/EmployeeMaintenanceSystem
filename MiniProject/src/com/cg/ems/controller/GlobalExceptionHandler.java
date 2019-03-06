package com.cg.ems.controller;



import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;



@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value= {Exception.class})
	protected ModelAndView handleConflict(Exception ex) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("message", "Internal error Occured...! Please try Again"+"\n Try to give the Appropriate values in the Details form");
		return model;
		
	}
}
