package com.example.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("/write")
	public void write(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Writer w = response.getWriter();
		w.write("test");
		w.flush();
		w.close();
	}
	@RequestMapping("/gogogo")
	public ModelAndView hello(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("gogogo");
		mv.addObject("content", "content");
		return mv;
	}
}
