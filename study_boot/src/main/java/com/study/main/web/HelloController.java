package com.study.main.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String[] hello() {
		String[] arr = {"밀키스","사이다","트래비","암바사"};
		return arr;
	}
}
