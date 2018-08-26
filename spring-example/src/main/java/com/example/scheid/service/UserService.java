package com.example.scheid.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.example.scheid.entity.User;
import com.example.scheid.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> findAllWS(){
		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<User[]> entity = restTemplate.getForEntity("http://localhost:8080/rest/user/", User[].class);
		 return Arrays.asList(entity.getBody());
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findUserById(Long id) {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new RuntimeException("Usuário não encontrado");
	}
	
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	
	public void saveAllUsers(List<User> users) {
		for (User user : users) {
			validadeMandatoryFields(user);
		}
		userRepository.saveAll(users);
	}
	
	public User saveUser(User user) {
		validadeMandatoryFields(user);
		return userRepository.save(user);
	}
	
	public void insertInitialUsers() {
		long count = userRepository.count();
		if(count > 0) {
			return;
		}
		createUser("João", "Silva", 20);
		createUser("Marcelo", "Rosa", 25);
		createUser("Wander", "Scheid", 30);
	}
	
	private void createUser(String firstName, String lastName, Integer age) {
		User user = new User();
		user.setAge(age);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		saveUser(user);
	}
	
	private void validadeMandatoryFields(User user) {
		if(user.getAge() == null) {
			throw new RuntimeException("Age is mandatory");
		}
		if(StringUtils.isEmpty(user.getFirstName())){
			throw new RuntimeException("First name is mandatory");
		}
		if(StringUtils.isEmpty(user.getLastName())){
			throw new RuntimeException("Last name is mandatory");
		}
	}
	
}
