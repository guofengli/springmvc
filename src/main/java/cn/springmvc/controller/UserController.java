package cn.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springmvc.model.User;
import cn.springmvc.service.UserService;

import com.google.gson.JsonObject;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

//	@RequestMapping("index")
//	public String index() {
//		User user = new User();
//		user.setNickname("你好");
//		System.out.println(userService.insertUser(user));
//		return "index";
//	}

	@RequestMapping(value = "/user/register")
	@ResponseBody
	public String registerUser(@RequestParam String username,
			@RequestParam String password, @RequestParam String email,
			@RequestParam String nickname, @RequestParam String occupation,
			@RequestParam String nation, @RequestParam String tel) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setNickname(nickname);
			user.setNation(nation);
			user.setTel(tel);
			user.setEmail(email);
			user.setOccupation(occupation);
			int result = 0;
			try{
				result = userService.insertUser(user);
			} catch(DuplicateKeyException e){
				System.out.println("原因" + e.getCause());
			}
			JsonObject userJson = new JsonObject();
			if(result == 1){
				userJson.addProperty("status", "1");
				userJson.addProperty("nickname", nickname);
				userJson.addProperty("username", username);
			}
			else{
				userJson.addProperty("status", "0");
			}
		return userJson.toString();
	}
	
	@RequestMapping(value = "/user/detectusername")
	@ResponseBody
	public String detectusername(@RequestParam String username) {
			User user = new User();
			JsonObject userJson = new JsonObject();
			if(username == null || "".equals(username)){
				userJson.addProperty("status", "0");
				return userJson.toString();
			} else {
				user.setUsername(username);
				String result = null;
				try{
					result = userService.selectUser(username);
				} catch(DuplicateKeyException e){
					System.out.println("原因" + e.getCause());
				}
	//			JsonObject userJson = new JsonObject();
				if("".equals(result)){
					userJson.addProperty("status", "1");
				}
				else{
					userJson.addProperty("status", "0");
				}
				return userJson.toString();
		}
	}
}