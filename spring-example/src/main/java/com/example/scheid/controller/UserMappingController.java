package com.example.scheid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.scheid.entity.UserForm;
import com.example.scheid.service.UserService;

@Controller
public class UserMappingController {

	@Autowired
	UserService userService;

	@PostMapping("/users/save")
	public String saveUsers(@ModelAttribute UserForm form, Model model) {
		userService.saveAllUsers(form.getUsers());
	    return "redirect:/users";
	}
	
	@GetMapping("/users/new")
	public String newUser() {
		return "newUser";
	}
	
	@GetMapping("/users")
	public String getUsers(Model model) {
		UserForm form = new UserForm();
		form.setUsers(userService.findAll());
		
		model.addAttribute("form", form);
		return "listUsers";
	}
	
	
	//Exemplo com ModelAndView:
	/*
	
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/index");
        mav.addObject("formName", "Anmeldeformular");
        return mav;
    }

    @RequestMapping(value = "listUsers", method = RequestMethod.GET)
    public ModelAndView showUsers() {
        ModelAndView mav = new ModelAndView("/listUsers");
        mav.addObject("users", userRepository.findAll());
        return mav;
    }
	*/
}
