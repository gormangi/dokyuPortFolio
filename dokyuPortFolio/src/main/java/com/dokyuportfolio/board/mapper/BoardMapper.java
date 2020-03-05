package com.dokyuportfolio.board.mapper;

import java.util.List;
import java.util.Map;

import com.dokyuportfolio.admin.vo.BoardVO;
import com.dokyuportfolio.admin.vo.CategoryVO;
import com.dokyuportfolio.admin.vo.FileVO;
import com.dokyuportfolio.board.vo.CommentVO;

public interface BoardMapper {
	
	FileVO selectFileInfo(FileVO vo) throws Exception;
	
	List<FileVO> selectFileList(BoardVO vo) throws Exception;
	
	BoardVO selectBoardDetail(BoardVO vo) throws Exception;
	
	CategoryVO selectCategory(BoardVO vo) throws Exception;
	
	int selectBoardCount(BoardVO vo) throws Exception;
	
	List<BoardVO> selectBoardList(BoardVO vo) throws Exception;

	BoardVO selectMainTopView() throws Exception;
	
	FileVO selectMainTopViewThumbnail(BoardVO vo) throws Exception;
	
	List<BoardVO> selectMainView(CategoryVO vo) throws Exception;
	
	CategoryVO selectMainViewSubject(CategoryVO vo) throws Exception;
	
	List<CommentVO> loadCommentList(BoardVO vo) throws Exception;
	
	int Commentwrite (CommentVO vo) throws Exception;
	
	int Commentdelete(CommentVO vo) throws Exception;
	
	List<BoardVO> selectSearchBoardList(BoardVO vo)throws Exception;
	
	int selectBoardSearchCount(BoardVO vo)throws Exception;
	
}
