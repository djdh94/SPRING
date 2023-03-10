package com.ict.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.controller.vo.UserVO;

@Controller
public class BasicController {

	@RequestMapping(value = "/goA")
	public String goA() {
		return "goA";
	}
	
	@RequestMapping(value = "/getData")
	public String getData(String data1,int data2,Model model) {
		model.addAttribute("data1",data1);
		model.addAttribute("data2",data2);
		return "getData";
	}
	
	@RequestMapping("/getMoney")
	public String getMoney(int won,Model model) {
		double total = won/36.16;
		System.out.println("입력한 금액은"+won+"입니다");
		System.out.println("입력한 금액에 따른 환전금액은"+total+"입니다");
		model.addAttribute("total",total);
		return "exchange";
	}
	
	@RequestMapping("/moneyForm")
	public String moneyForm() {
		return "moneyForm";
	}
	
	@RequestMapping("/dataForm")
	public String dataForm() {
		return "dataForm";
	}
	
	@GetMapping("/score")
	public String score() {
		return "score";
	}
	@PostMapping("/score")
	public String score2(int a,int b,int c,int d, int e,Model model) {
		int total=a+b+c+d+e;
		double avg = total/5.0;
		model.addAttribute("total",total);
		model.addAttribute("avg",avg);
		
		return "result";
	}
	@GetMapping("/page/{pageNum}")
	public String page(Model model,@PathVariable int pageNum) {
		model.addAttribute("page",pageNum);
		return "page";
	}
	
	@GetMapping("/rate/{won}")
	public String rate(@PathVariable int won,Model model) {
		model.addAttribute("won",won/36.16);
		return "rate";
	}
	
	@GetMapping("/getList")
	public String getList(@RequestParam ArrayList<String> array,Model model) {
		model.addAttribute("array",array);
		return "getList";
	}
	@PostMapping("/userInfo")
		public String getUserInfo(UserVO userVO,Model model) {
		model.addAttribute("userVO",userVO);
		return "user";
		}
	
	@GetMapping("/userInfo")
	public String userinfo() {
		return "userForm";
	}
}
