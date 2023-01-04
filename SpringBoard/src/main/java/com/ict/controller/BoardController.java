package com.ict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ict.domain.BoardVO;
import com.ict.mapper.BoardMapper;

@Controller
public class BoardController {
	
	@Autowired
	private BoardMapper boardmapper;
	
	@GetMapping("/boardList")
	public String getboardList(Model model) {
		model.addAttribute("boardList",boardmapper.getList());
		return "boardList";
	}
	
	@GetMapping("/boardDetail/{bno}")
	public String getboardDetail(@PathVariable long bno,Model model) {
		model.addAttribute("board",boardmapper.select(bno));
		return "boardDetail";
	}
	
	@GetMapping("/boardInsert")
	public String getboardInsertForm() {
		return "boardForm";
	}
	
	@PostMapping("/boardInsert")
	public String getboardInsert(BoardVO vo) {
		boardmapper.insert(vo); 
		return "redirect:/boardList";
	}
	
	@PostMapping("/boardDelete")
	public String getboardDelete(long bno) {
		boardmapper.delete(bno);
		return "redirect:/boardList";
	}
	
	@PostMapping("/boardUpdateForm")
	public String getboardUpdateForm(long bno,Model model) {
		model.addAttribute("board",boardmapper.select(bno));
		return "boardUpdateForm";
	}
	
	@PostMapping("/boardUpdate")
	public String getboardUpdate(BoardVO vo) {
		boardmapper.update(vo);
		return "redirect:/boardDetail/"+vo.getBno();
	}
}
