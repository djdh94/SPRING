package com.ict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.SearchCriteria;

public interface BoardMapper {

	public List<BoardVO> getList(SearchCriteria cri);
	
	public void insert(BoardVO vo);
	
	public BoardVO select(long bno);
	
	public void delete(long bno);
	
	public void update(BoardVO vo);
	
	public int countPageNum(SearchCriteria cri);
	
	public void updateReplyCount(@Param("bno") Long bno,
								@Param("amount") int amount);
}
