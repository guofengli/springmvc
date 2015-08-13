package cn.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.springmvc.model.User;
import cn.springmvc.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@RequestMapping("index")
	public String index() {
		User user = new User();
		user.setNickname("你好");
		user.setState(2);
		System.out.println(userService.insertUser(user));
		return "index";
	}

}