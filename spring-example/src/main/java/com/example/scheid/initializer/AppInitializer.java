package com.example.scheid.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.scheid.service.UserService;

@Component
public class AppInitializer implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	UserService userService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		userService.insertInitialUsers();
	}

	
	
}
