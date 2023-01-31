package com.ict.service;

import java.util.List;

import com.ict.domain.ReplyVO;

public interface ReplyService {

	public void addRepaly(ReplyVO vo);
	
	public List<ReplyVO> listReply(Long bno);
	
	public void modifyReply(ReplyVO vo);
	
	public void removeReply(Long rno);
}
