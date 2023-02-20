package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.ReplyVO;
import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;

@Service
@Component
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyMapper mapper;
	
	// 댓글작성시 board_tbl 쪽에도 관여해야 하므로 board테이블을 수정하는 mapper 추가선언
	@Autowired
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public void addRepaly(ReplyVO vo) {
		mapper.create(vo);
		boardMapper.updateReplyCount(vo.getBno(),1);
		
	}

	@Override
	public List<ReplyVO> listReply(Long bno) {
		return mapper.getList(bno) ;
	}

	@Override
	public void modifyReply(ReplyVO vo) {
		mapper.update(vo);
	}

	@Transactional
	@Override
	public void removeReply(Long rno) {
		
		// 글 삭제 전에 먼저 bno번 채취해놓고
		Long bno =  (long) mapper.getBno(rno);
		// 다음 글 삭제해야 문제없이 글번호 가져옴
		mapper.delete(rno);
		boardMapper.updateReplyCount(bno,-1);
	}
	
	

}
