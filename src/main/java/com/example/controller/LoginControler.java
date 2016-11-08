package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.model.User;
import com.example.utils.Return2AndriodFormat;

@Controller
public class LoginControler {
	
	@RequestMapping("/login")
	@ResponseBody
	
	public String login(User user) {
		
		Map<String, Object> result = new HashMap<>();
		if(user.getPassword() == null || user.getUsername() == null) {
			return JSONObject.toJSONString(Return2AndriodFormat.getResult(400, "用户名或者登录密码失败", result),SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);

		}
		return JSONObject.toJSONString(Return2AndriodFormat.getResult(200, "成功登录", result),SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
	}
}
