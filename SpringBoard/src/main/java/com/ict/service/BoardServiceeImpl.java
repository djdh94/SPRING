package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.BoardVO;
import com.ict.domain.SearchCriteria;
import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;

@Service
@Component
public class BoardServiceeImpl implements BoardService{

	@Autowired
	private BoardMapper boardmapper;
	
	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public List<BoardVO> getList(SearchCriteria cri) {
		return boardmapper.getList(cri);
		 
	}

	@Override
	public int countPageNum(SearchCriteria cri) {
		return boardmapper.countPageNum(cri);
	}

	@Override
	public BoardVO select(long bno) {
		
		return boardmapper.select(bno);
	}

	@Override
	public void insert(BoardVO vo) {
		boardmapper.insert(vo);
		
	}

//	@Transactional
	@Override
	public void delete(long bno) {
		// 댓글 먼저 삭제후 글삭제
		replyMapper.deleteAllReplies(bno);
		boardmapper.delete(bno);
		
	}

	@Override
	public void update(BoardVO vo) {
		boardmapper.update(vo);
		
	}
	
	
	
	
}
