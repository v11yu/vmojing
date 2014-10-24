package com.vmojing.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vmojing.mongodb.domain.Topic;

@Controller
@RequestMapping("/topic")
public class TopicController {
	@RequestMapping(method=RequestMethod.GET)
	public void topic(){
		
	}
	@RequestMapping("/hello")
	public @ResponseBody String simple() {
		return "Hello world!";
	}
	@RequestMapping("/create")
	public Topic createTopic(){
		return null;
		
	}
}
