package com.ict.persistance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.domain.BoardVO;
import com.ict.domain.SearchCriteria;
import com.ict.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper boardmapper;
	
	@Test
	public void testGetTime2() {
		SearchCriteria cri = new SearchCriteria();
		cri.setKeyword("테스트");
		cri.setSearchType("t");
		boardmapper.getList(cri);
	}
	
	//@Test
	public void Insert() {
		BoardVO vo = new BoardVO();
		log.info("채워넣기전:"+vo);
		vo.setTitle("새로넣는글");
		vo.setContent("새로넣는본문");
		vo.setWriter("새로넣는글쓴이");
		log.info("채워넣은후:"+vo);
		boardmapper.insert(vo);
	}
	
	//@Test
	public void select() {
		log.info(boardmapper.select(5));
	}
	
	//@Test
	public void delete() {
		boardmapper.delete(6);
	}
	
	//@Test
	public void update() {
		BoardVO board = new BoardVO();
		board.setTitle("바꿀제목");
		board.setContent("바꿀내용");
		board.setBno(1);
		boardmapper.update(board);
		
	}
}
