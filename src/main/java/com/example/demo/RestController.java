package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	@Autowired
	Restservice restservice;

	@Autowired
	Accountservice accountservice;

	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		 new Greeting(counter.incrementAndGet(), String.format(template, name));
		return "String";
	}
	@PostMapping("/register")
	@CrossOrigin(origins = "http://localhost:3000")
	public String registerUser(@RequestBody String temp) {
		System.out.println(temp);
		restservice.saveUser(temp);
		return "Success";
	}

	@PostMapping("/getUser")
	@CrossOrigin(origins = "http://localhost:3000")
	public String getUser(@RequestBody String user) {
		return restservice.getUser(user);
	}

	@PostMapping("/addAccount")
	@CrossOrigin(origins = "http://localhost:3000")
	public String addAccount(@RequestBody String account) {
		accountservice.saveAccount(account);
		return "Success";
	}

	@PostMapping("/validateUser")
	@CrossOrigin(origins = "http://localhost:3000")
	public String validateUser(@RequestBody String user) {
		return restservice.validateUser(user);
	}

	@PostMapping("/updateUser")
	@CrossOrigin(origins = "http://localhost:3000")
	public String updateUser(@RequestBody String user) {

		return restservice.updateUser(user);
	}

	@PostMapping("/getAccounts")
	@CrossOrigin(origins = "http://localhost:3000")
	public String getAccounts(@RequestBody String user) {
		System.out.println(accountservice.getAccounts(user) + "----output");
		return accountservice.getAccounts(user);
	}
}
