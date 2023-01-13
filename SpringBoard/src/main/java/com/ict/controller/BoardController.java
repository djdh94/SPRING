package com.ict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.PageMaker;
import com.ict.domain.SearchCriteria;
import com.ict.mapper.BoardMapper;
import com.ict.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/boardList") 
	public String getboardList(SearchCriteria cri,Model model) {
		model.addAttribute("boardList",service.getList(cri));
		
		// 버튼 처리를 위해 추가로 페이지메이커 생성
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		//131 대신 실제로 db내 글개수를 받아옴
		pageMaker.setTotalBoard(service.countPageNum(cri));
		model.addAttribute("pageMaker",pageMaker);
		return "boardList";
	}
	
	@GetMapping("/boardDetail/{bno}")   
	public String getboardDetail(@PathVariable long bno,Model model) {
		model.addAttribute("board",service.select(bno));
		return "boardDetail";
	}
	
	@GetMapping("/boardInsert")
	public String getboardInsertForm() {
		return "boardForm";
	}
	
  	@PostMapping("/boardInsert")
	public String getboardInsert(BoardVO vo) {
		service.insert(vo); 
		return "redirect:/boardList";
	}
	
	@PostMapping("/boardDelete")
	public String getboardDelete(long bno,SearchCriteria cri,RedirectAttributes rttr) {
		service.delete(bno);
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		return "redirect:/boardList";
	}
	
	@PostMapping("/boardUpdateForm")
	public String getboardUpdateForm(long bno,Model model) {
		model.addAttribute("board",service.select(bno));
		return "boardUpdateForm";
	}
	
	@PostMapping("/boardUpdate")
	public String getboardUpdate(BoardVO vo,SearchCriteria cri,RedirectAttributes rttr) {
		service.update(vo);
		// rttr.addAttribute("파라미터명","전달자료")
		//는 호출되면 redirect 주소 뒤에 파라미터를 붙임
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		return "redirect:/boardDetail/"+vo.getBno();
	}
}
