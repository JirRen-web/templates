package com.example.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.excel.TestBean;


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
	
	@RequestMapping("/xml")
	public ModelAndView xml(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//输出的应用类型
//		Response.ContentType = "application/vnd.ms-excel";
//		response.setContentType("application/vnd.ms-excel");
		//设定编码方式，若输出的excel有乱码，可优先从编码方面解决
//		Response.Charset = "gb2312";
//		Response.ContentEncoding = System.Text.Encoding.UTF8; 
		//关闭ViewState，此属性在Page中
//		EnableViewState = false; 
		//filenames是自定义的文件名
//		Response.AppendHeader("Content-Disposition", "attachment;filename=" + filenames);
//		String filenames = "test";
//		response.addHeader("Content-Disposition", "attachment;filename=" + filenames);
		//content是步骤1的html，注意是string类型
//		Response.Write(content);
//		Response.End();
	    Properties p =	System.getProperties();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("xml");
		return mv;
	}
	
	@RequestMapping("/params")
	@ResponseBody
	public TestBean params(TestBean bean) throws IOException {
		return bean;
	}
	
}
