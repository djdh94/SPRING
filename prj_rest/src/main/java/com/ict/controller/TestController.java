package com.ict.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.domain.TestVO;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello Hello";
		
	}
	
	@RequestMapping("/sendVO")
	public TestVO sendTestVO() {
		TestVO testVO = new TestVO();
		testVO.setName("채종훈");
		testVO.setAge(21);
		testVO.setMno(1);
		return testVO;
	}
	
	@RequestMapping("sendVOList")
	public List<TestVO> sendVOList(){
		List<TestVO> list = new ArrayList<TestVO>();
		for(int i=0; i<10; i++) {
			TestVO vo = new TestVO();
			vo.setMno(i);
			vo.setName(i+"종훈");
			vo.setAge(20+i);
			list.add(vo);
		}
		return list;
	}
	
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){
		return 
				new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<TestVO>> sendListNot(){
		List<TestVO> list = new ArrayList<TestVO>();
		for(int i=0; i<10; i++) {
			TestVO vo = new TestVO();
			vo.setMno(i);
			vo.setName(i+"채종훈");
			vo.setAge(20+i);
			list.add(vo);
		}
		return 
				new ResponseEntity<List<TestVO>>(list,HttpStatus.NOT_FOUND);
	}

}
